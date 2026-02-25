package com.forumhub.dto;

public class TopicoAtualizacaoDTO {

    private String titulo;
    private String mensagem;
    private String curso;

    // Construtor vazio (obrigatório para o Spring)
    public TopicoAtualizacaoDTO() {
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}