package com.cantina.service;

import com.cantina.dto.PedidoRequestDTO;
import com.cantina.dto.PedidoResponseDTO;
import com.cantina.model.Pedido;
import com.cantina.model.Usuario;
import com.cantina.repository.PedidoRepository;
import com.cantina.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public PedidoResponseDTO criarPedido (@RequestBody PedidoRequestDTO pedido){
        Pedido pedido1 = new Pedido();


        pedido1.setProdutos(pedido.produtos());

        Usuario usuario = usuarioRepository.findById(pedido.usuarioId()).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));

        pedido1.setUsuario(usuario);



        return toDto(pedidoRepository.save(pedido1));
   }

   public List<PedidoResponseDTO> listarPedidos (){
        return pedidoRepository.findAll().stream().map(this::toDto).toList();
   }

   public PedidoResponseDTO listarPedidoPorId(Long id){
        return toDto(pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado")));
   }

   public void deletarPedido(Long id){
        pedidoRepository.deleteById(id);
   }

   public PedidoResponseDTO atualizarPedido(Long id, PedidoRequestDTO pedido){
        Pedido pedido1 = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido1.setProdutos(pedido.produtos());
       Usuario usuario = usuarioRepository.findById(pedido.usuarioId()).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));

       pedido1.setUsuario(usuario);

       return toDto(pedidoRepository.save(pedido1));
   }

    private PedidoResponseDTO toDto(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getProdutos(),
                pedido.getUsuario().getId()
                );
    }

}
