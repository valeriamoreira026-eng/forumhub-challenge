package com.forumhub.controller;

import com.forumhub.domain.Topico;
import com.forumhub.dto.TopicoAtualizacaoDTO;
import com.forumhub.dto.TopicoCadastroDTO;
import com.forumhub.dto.TopicoListagemDTO;
import com.forumhub.service.TopicoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    // ✅ LISTAR TODOS
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TopicoListagemDTO> listar() {
        return service.listarTodos()
                .stream()
                .map(TopicoListagemDTO::new)
                .toList();
    }

    // ✅ BUSCAR POR ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TopicoListagemDTO buscar(@PathVariable Long id) {
        return new TopicoListagemDTO(service.buscarPorId(id));
    }

    // ✅ CRIAR TÓPICO
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Topico cadastrar(@RequestBody TopicoCadastroDTO dto) {
        return service.cadastrar(dto);
    }

    // ✅ ATUALIZAR TÓPICO
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Topico atualizar(
            @PathVariable Long id,
            @RequestBody TopicoAtualizacaoDTO dto) {
        return service.atualizar(id, dto);
    }

    // ✅ DELETAR TÓPICO (NOVO)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}