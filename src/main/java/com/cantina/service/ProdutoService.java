package com.cantina.service;

import com.cantina.dto.ProdutoRequestDTO;
import com.cantina.dto.ProdutoResponseDTO;
import com.cantina.exceptions.ProdutoNotFoundException;
import com.cantina.model.CategoriaProduto;
import com.cantina.model.Produto;
import com.cantina.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

        Produto produto = produtoRepository.findById(id).orElseThrow(()-> new ProdutoNotFoundException(id));

        return toDto(produto);
    }

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produto){

        Produto produto1 = new Produto();
        produto1.setNome(produto.nome());
        produto1.setQuantidade(produto.quantidade());
        produto1.setValor(produto.valor());
        produto1.setCategoria(produto.categoriaProduto());
        produto1.setUrl(produto.url());
        produto1.setDisponivel(produto.disponivel());

        return toDto(produtoRepository.save(produto1));
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produto){


        Produto produto1 = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));


        produto1.setNome(produto.nome());
        produto1.setValor(produto.valor());
        produto1.setQuantidade(produto.quantidade());
        produto1.setCategoria(produto.categoriaProduto());
        produto1.setUrl(produto1.getUrl());
        produto1.setDisponivel(produto.disponivel());

        return toDto(produtoRepository.save(produto1));
        
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public List<ProdutoResponseDTO> listarSalgados (){
       return produtoRepository.findByCategoria(CategoriaProduto.SALGADO).stream().map(this::toDto).toList();
    }

    public List<ProdutoResponseDTO> listarDoces (){
       return produtoRepository.findByCategoria(CategoriaProduto.DOCE).stream().map(this::toDto).toList();
    }

    public List<ProdutoResponseDTO> listarBebidas (){
       return produtoRepository.findByCategoria(CategoriaProduto.BEBIDA).stream().map(this::toDto).toList();
    }

    private ProdutoResponseDTO toDto(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getQuantidade(),
                produto.getCategoria(),
                produto.getUrl(),
                produto.getDisponivel()
        );
    }
}
