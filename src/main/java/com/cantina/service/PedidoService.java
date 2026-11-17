package com.cantina.service;

import com.cantina.dto.ItemPedidoDTO;
import com.cantina.dto.PedidoRequestDTO;
import com.cantina.dto.PedidoResponseDTO;
import com.cantina.exceptions.PedidoNotFoundException;
import com.cantina.exceptions.ProdutoNotFoundException;
import com.cantina.exceptions.UsuarioNotFoundException;
import com.cantina.model.Pedido;
import com.cantina.model.Produto;
import com.cantina.model.Usuario;
import com.cantina.repository.PedidoRepository;
import com.cantina.repository.ProdutoRepository;
import com.cantina.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    public PedidoResponseDTO criarPedido (PedidoRequestDTO pedido){
        Pedido pedido1 = new Pedido();
        List<Produto> produtos = new ArrayList<>();

        for (ItemPedidoDTO item : pedido.produtos()) {
            Produto produto = produtoRepository.findByNome(item.nome())
                    .orElseThrow(() -> new ProdutoNotFoundException(item.nome()));

            produtos.add(produto);

            produto.setQuantidade(produto.getQuantidade().subtract(item.quantidade()));
        }

        pedido1.setProdutos(produtos);

        Usuario usuario = usuarioRepository.findById(pedido.usuarioId()).orElseThrow(()-> new UsuarioNotFoundException(pedido.usuarioId()));

        pedido1.setUsuario(usuario);



        return toDto(pedidoRepository.save(pedido1));
   }

   public List<PedidoResponseDTO> listarPedidos (){
        return pedidoRepository.findAll().stream().map(this::toDto).toList();
   }

   public PedidoResponseDTO listarPedidoPorId(Long id){
        return toDto(pedidoRepository.findById(id).orElseThrow(() -> new PedidoNotFoundException(id)));
   }

   public void deletarPedido(Long id){
        pedidoRepository.deleteById(id);
   }

    public PedidoResponseDTO atualizarPedido(Long id, PedidoRequestDTO pedido){
        Pedido pedido1 = pedidoRepository.findById(id).orElseThrow(() -> new PedidoNotFoundException(id));

        List<Produto> produtos = new ArrayList<>();

        for (ItemPedidoDTO item : pedido.produtos()) {
            Produto produto = produtoRepository.findByNome(item.nome())
                    .orElseThrow(() -> new ProdutoNotFoundException(id));

            produtos.add(produto);
        }

        pedido1.setProdutos(produtos);

        Usuario usuario = usuarioRepository.findById(pedido.usuarioId()).orElseThrow(()-> new UsuarioNotFoundException(pedido.usuarioId()));

        pedido1.setUsuario(usuario);

        return toDto(pedidoRepository.save(pedido1));
    }

    private PedidoResponseDTO toDto(Pedido pedido) {
        List<ItemPedidoDTO> itens = pedido.getProdutos().stream()
                .collect(Collectors.groupingBy(Produto::getNome, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new ItemPedidoDTO(e.getKey(), BigInteger.valueOf(e.getValue())))
                .toList();

        return new PedidoResponseDTO(pedido.getId(), itens, pedido.getUsuario().getId());
    }

}
