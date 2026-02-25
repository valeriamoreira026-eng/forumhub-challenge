package com.forumhub.infra;

import com.forumhub.repository.UsuarioRepository;
import com.forumhub.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("========== FILTER SECURITY ==========");
        System.out.println("URI: " + request.getRequestURI());
        System.out.println("Método: " + request.getMethod());

        var tokenJWT = recuperarToken(request);
        System.out.println("Token recebido: " + (tokenJWT != null ? "SIM" : "NÃO"));

        if (tokenJWT != null) {
            try {
                String subject = tokenService.getSubject(tokenJWT);
                System.out.println("Subject do token: " + subject);

                // CORREÇÃO AQUI: usar o método que retorna UserDetails
                UserDetails usuario = usuarioRepository.findByEmailIgnoreCase(subject);
                System.out.println("Usuário encontrado: " + (usuario != null ? usuario.getUsername() : "NÃO ENCONTRADO"));

                if (usuario != null) {
                    System.out.println("Autoridades do usuário: " + usuario.getAuthorities());

                    var authentication = new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Autenticação setada no contexto");
                }
            } catch (Exception e) {
                System.out.println("ERRO ao processar token: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Nenhum token encontrado na requisição");
        }

        System.out.println("=====================================");
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        System.out.println("Header Authorization: " + authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }

        return authorizationHeader.replace("Bearer ", "");
    }
}