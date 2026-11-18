package com.cantina.controller;

import com.cantina.dto.ProdutoRequestDTO;
import com.cantina.dto.ProdutoResponseDTO;
import com.cantina.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping()
    public List<ProdutoResponseDTO> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("{id}")
    public ProdutoResponseDTO listarProdutoPorId(@PathVariable Long id){
        return produtoService.listarProdutoPorId(id);
    }

    @PostMapping()
    public ProdutoResponseDTO criarProduto(@Valid @RequestBody ProdutoRequestDTO produto){
        return produtoService.criarProduto(produto);
    }

    @PutMapping("{id}")
    public ProdutoResponseDTO atualizarProduto (@PathVariable Long id,@Valid @RequestBody ProdutoRequestDTO produto){
        return produtoService.atualizarProduto(id, produto);
    }

    @GetMapping("salgados")
    public List<ProdutoResponseDTO> listarSalgados(){
        return produtoService.listarSalgados();
    }

    @GetMapping("doces")
    public List<ProdutoResponseDTO> listarDoces (){
        return produtoService.listarDoces();
    }

    @GetMapping("bebidas")
    public List<ProdutoResponseDTO> listarBebidas (){
        return produtoService.listarBebidas();
    }





    @DeleteMapping("{id}")
    public void deletarProduto (@PathVariable Long id){
        produtoService.deletarProduto(id);
    }

}
