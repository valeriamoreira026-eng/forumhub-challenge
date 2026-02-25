package com.forumhub.service;

import com.forumhub.domain.Topico;
import com.forumhub.domain.Usuario;
import com.forumhub.dto.TopicoAtualizacaoDTO;
import com.forumhub.dto.TopicoCadastroDTO;
import com.forumhub.infra.UsuarioLogado;
import com.forumhub.repository.TopicoRepository;
import com.forumhub.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioLogado usuarioLogado;

    public TopicoService(TopicoRepository topicoRepository,
                         UsuarioRepository usuarioRepository,
                         UsuarioLogado usuarioLogado) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioLogado = usuarioLogado;
    }

    // ✅ LISTAR TODOS
    public List<Topico> listarTodos() {
        return topicoRepository.findAll();
    }

    // ✅ BUSCAR POR ID
    public Topico buscarPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
    }

    // ✅ CADASTRAR
    public Topico cadastrar(TopicoCadastroDTO dto) {
        Usuario autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());
        topico.setCurso(dto.getCurso());
        topico.setAutor(autor);

        return topicoRepository.save(topico);
    }

    // ✅ ATUALIZAR
    public Topico atualizar(Long id, TopicoAtualizacaoDTO dto) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        Usuario usuarioLogado = this.usuarioLogado.get();

        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            throw new RuntimeException("Você não tem permissão para atualizar este tópico");
        }

        if (dto.getTitulo() != null) {
            topico.setTitulo(dto.getTitulo());
        }

        if (dto.getMensagem() != null) {
            topico.setMensagem(dto.getMensagem());
        }

        if (dto.getCurso() != null) {
            topico.setCurso(dto.getCurso());
        }

        return topicoRepository.save(topico);
    }

    // ✅ DELETAR (NOVO)
    public void deletar(Long id) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        Usuario usuarioLogado = this.usuarioLogado.get();

        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            throw new RuntimeException("Você não tem permissão para deletar este tópico");
        }

        topicoRepository.deleteById(id);
    }
}