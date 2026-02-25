package com.forumhub.controller;

import com.forumhub.domain.Usuario;
import com.forumhub.dto.UsuarioDTO;
import com.forumhub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // ← ADICIONADO

    @PostMapping
    public ResponseEntity<Usuario> criar(
            @RequestBody @Valid UsuarioDTO dto,
            UriComponentsBuilder uriBuilder) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));  // ← CRIPTOGRAFA A SENHA

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuarioSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(usuarioSalvo);
    }
}