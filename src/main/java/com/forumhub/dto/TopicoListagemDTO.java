package com.forumhub.dto;

import com.forumhub.domain.Topico;

public record TopicoListagemDTO(
        Long id,
        String titulo,
        String mensagem,
        String autorNome,
        String curso
) {

    public TopicoListagemDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor().getNome(),
                topico.getCurso()
        );
    }
}
