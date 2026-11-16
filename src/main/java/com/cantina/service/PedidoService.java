package com.cantina.service;

import com.cantina.dto.ItemPedidoDTO;
import com.cantina.dto.PedidoRequestDTO;
import com.cantina.dto.PedidoResponseDTO;
import com.cantina.model.Pedido;
import com.cantina.model.Produto;
import com.cantina.model.Usuario;
import com.cantina.repository.PedidoRepository;
import com.cantina.repository.ProdutoRepository;
import com.cantina.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }


    public PedidoResponseDTO criarPedido (@RequestBody PedidoRequestDTO pedido){
        Pedido pedido1 = new Pedido();
        List<Produto> produtos = new ArrayList<>();

        for (ItemPedidoDTO item : pedido.produtos()) {
            Produto produto = produtoRepository.findByNome(item.nome())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            produtos.add(produto);
        }

        pedido1.setProdutos(produtos);


        pedido1.setProdutos(produtos);

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

        List<Produto> produtos = new ArrayList<>();

        for (ItemPedidoDTO item : pedido.produtos()) {
            Produto produto = produtoRepository.findByNome(item.nome())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            produtos.add(produto);
        }

        pedido1.setProdutos(produtos);

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
