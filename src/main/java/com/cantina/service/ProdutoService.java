package com.cantina.service;

import com.cantina.dto.ProdutoRequestDTO;
import com.cantina.dto.ProdutoResponseDTO;
import com.cantina.model.Produto;
import com.cantina.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponseDTO> listarProdutos (){
        return produtoRepository.findAll().stream().map(this::toDto).toList();
    }

    public ProdutoResponseDTO listarProdutoPorId(Long id){

        Produto produto = produtoRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));

        return toDto(produto);
    }

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produto){

        Produto produto1 = new Produto();
        produto1.setNome(produto.nome());
        produto1.setQuantidade(produto.quantidade());
        produto1.setValor(produto.valor());

        return toDto(produtoRepository.save(produto1));
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produto){


        Produto produto1 = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));


        produto1.setNome(produto.nome());
        produto1.setValor(produto.valor());
        produto1.setQuantidade(produto.quantidade());

        return toDto(produtoRepository.save(produto1));
        
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

    private ProdutoResponseDTO toDto(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getQuantidade()
        );
    }
}
