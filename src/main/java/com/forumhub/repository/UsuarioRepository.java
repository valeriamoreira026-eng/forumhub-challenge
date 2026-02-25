package com.forumhub.repository;

import com.forumhub.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    // Método que retorna UserDetails para o Spring Security
    UserDetails findByEmailIgnoreCase(String email);
}