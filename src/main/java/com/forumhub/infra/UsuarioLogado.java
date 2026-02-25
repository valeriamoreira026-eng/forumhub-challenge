package com.forumhub.infra;

import com.forumhub.domain.Usuario;
import com.forumhub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UsuarioLogado {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario get() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuário não autenticado");
        }

        var userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        // Buscar o usuário pelo email no banco
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}