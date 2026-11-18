package com.cantina.controller;

import com.cantina.dto.LoginRequestDTO;
import com.cantina.dto.UsuarioRequestDTO;
import com.cantina.dto.UsuarioResponseDTO;
import com.cantina.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("{id}")
    public UsuarioResponseDTO listarUsuarioPorId(@PathVariable Long id){
        return usuarioService.listarUsuarioPorId(id);
    }

    @PostMapping()
    public UsuarioResponseDTO criarUsuario(@RequestBody UsuarioRequestDTO usuario){
        return usuarioService.criarUsuario(usuario);
    }

    @PutMapping("{id}")
    public UsuarioResponseDTO atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuario){
        return usuarioService.atualizarUsuario(id, usuario);
    }

    @DeleteMapping("{id}")
    public void deletarUsuario (@PathVariable Long id){
        usuarioService.deletarUsuario(id);
    }

    @PostMapping("/login")
    public UsuarioResponseDTO login (@RequestBody LoginRequestDTO login){
        return usuarioService.login(login);
    }
}
