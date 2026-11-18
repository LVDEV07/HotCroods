package com.cantina.controller;

import com.cantina.dto.ItemPedidoDTO;
import com.cantina.dto.PedidoRequestDTO;
import com.cantina.dto.PedidoResponseDTO;
import com.cantina.model.Pedido;
import com.cantina.model.Produto;
import com.cantina.repository.ProdutoRepository;
import com.cantina.service.PedidoService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    private final ProdutoRepository produtoRepository;

    public PedidoController(PedidoService pedidoService, ProdutoRepository produtoRepository) {
        this.pedidoService = pedidoService;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public PedidoResponseDTO criarPedido(@Valid @RequestBody PedidoRequestDTO pedido){

        return pedidoService.criarPedido(pedido);
    }

    @GetMapping("{id}")
    public PedidoResponseDTO listarPedidoPorId(@PathVariable Long id){
        return pedidoService.listarPedidoPorId(id);
    }

    @GetMapping
    public List<PedidoResponseDTO> listarPedidos(){
        return pedidoService.listarPedidos();
    }

    @PutMapping("{id}")
    public PedidoResponseDTO atualizarPedido(@PathVariable Long id,@Valid @RequestBody PedidoRequestDTO pedido){
        return pedidoService.atualizarPedido(id,pedido);
    }

    @DeleteMapping("{id}")
    public void deletarPedido(@PathVariable Long id){
         pedidoService.deletarPedido(id);
    }


}
