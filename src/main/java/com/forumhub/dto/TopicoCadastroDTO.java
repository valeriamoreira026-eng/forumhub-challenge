package com.forumhub.dto;

public class TopicoCadastroDTO {

    private String titulo;
    private String mensagem;
    private Long autorId;
    private String curso;

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Long getAutorId() {
        return autorId;
    }

    public String getCurso() {
        return curso;
    }
}
