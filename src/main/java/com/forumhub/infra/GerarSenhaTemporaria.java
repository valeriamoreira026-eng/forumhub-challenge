package com.forumhub.infra;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenhaTemporaria {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = "123456";
        String senhaCriptografada = encoder.encode(senha);

        System.out.println("==================================");
        System.out.println("Senha original: " + senha);
        System.out.println("Senha criptografada: " + senhaCriptografada);
        System.out.println("==================================");
    }
}