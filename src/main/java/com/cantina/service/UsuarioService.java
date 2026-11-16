package com.cantina.service;

import com.cantina.dto.*;
import com.cantina.model.Pedido;
import com.cantina.model.Produto;
import com.cantina.model.Usuario;
import com.cantina.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> listarUsuarios(){
        return usuarioRepository.findAll().stream().map(this::toDto).toList();
    }

    public UsuarioResponseDTO listarUsuarioPorId(Long id){
        return toDto(usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuario não encontrado")));
    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponseDTO criarUsuario (UsuarioRequestDTO usuario){
        Usuario usuario1 = new Usuario();
        usuario1.setCpf(usuario.cpf());
        usuario1.setNome(usuario.nome());
        usuario1.setEmail(usuario.email());
        usuario1.setSenha(usuario.senha());


        return toDto(usuarioRepository.save(usuario1));
    }

    public UsuarioResponseDTO atualizarUsuario (Long id, UsuarioRequestDTO usuario){
        Usuario usuario1 = usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuario não " +
                "encontrado"));

        usuario1.setCpf(usuario.cpf());
        usuario1.setNome(usuario.nome());
        usuario1.setEmail(usuario.email());
        usuario1.setSenha(usuario.senha());

        return toDto(usuarioRepository.save(usuario1));
    }

    private UsuarioResponseDTO toDto(Usuario usuario) {

        List<PedidoResponseUsuarioDTO> pedidos = Optional.ofNullable(usuario.getPedidos())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(this::toDtoPedido)
                .toList();
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getSenha(),
                pedidos
        );
    }

    private PedidoResponseUsuarioDTO toDtoPedido(Pedido pedido) {
        return new PedidoResponseUsuarioDTO(
                pedido.getId(),
                pedido.getProdutos()
        );
    }
}
