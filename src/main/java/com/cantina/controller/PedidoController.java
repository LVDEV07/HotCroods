package com.cantina.controller;

import com.cantina.dto.PedidoRequestDTO;
import com.cantina.dto.PedidoResponseDTO;
import com.cantina.model.Pedido;
import com.cantina.service.PedidoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public PedidoResponseDTO criarPedido(@RequestBody PedidoRequestDTO pedido){
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
    public PedidoResponseDTO atualizarPedido(@PathVariable Long id, @RequestBody PedidoRequestDTO pedido){
        return pedidoService.atualizarPedido(id,pedido);
    }

    @DeleteMapping("{id}")
    public void deletarPedido(@PathVariable Long id){
         pedidoService.deletarPedido(id);
    }


}
