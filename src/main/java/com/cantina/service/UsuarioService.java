package com.cantina.service;

import com.cantina.dto.*;
import com.cantina.model.Pedido;
import com.cantina.model.Produto;
import com.cantina.model.Usuario;
import com.cantina.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getSenha(),
                usuario.getPedidos().stream().map(this::toDtoPedido).toList()
        );
    }

    private PedidoResponseUsuarioDTO toDtoPedido(Pedido pedido) {
        return new PedidoResponseUsuarioDTO(
                pedido.getId(),
                pedido.getProdutos()
        );
    }
}
