package com.forumhub.controller;

import com.forumhub.domain.Usuario;
import com.forumhub.dto.DadosLogin;
import com.forumhub.dto.TokenDTO;
import com.forumhub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid DadosLogin dados) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        dados.email(),
                        dados.senha()
                );

        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        String token = tokenService.gerarToken(usuario.getEmail());

        // ✅ AGORA compatível com seu DTO
        return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
    }
}