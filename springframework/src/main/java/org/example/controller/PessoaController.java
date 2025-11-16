package org.example.controller;

import jakarta.validation.Valid;
import org.example.adapter.PessoaControllerAdapter;
import org.example.dto.request.PessoaRequest;
import org.example.dto.response.PessoaResponse;
import org.example.entity.Pessoa;
import org.example.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa/v1")
public class PessoaController {
    private final PessoaRepository repository;

    public PessoaController(PessoaRepository repository) {
        this.repository = repository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PessoaResponse create(@Valid @RequestBody PessoaRequest request) {
        Pessoa pessoa = PessoaControllerAdapter.cast(request);
        return PessoaControllerAdapter.cast(repository.create(pessoa));
    }

    @GetMapping("/{id}")
    public PessoaResponse readById(@PathVariable("id") String id) {
        return PessoaControllerAdapter.cast(repository.readById(id));
    }

    @GetMapping
    public List<PessoaResponse> readByPage(
                @RequestParam(
                        name = "page", required = false, defaultValue = "0"
                ) int page,
                @RequestParam(
                        name = "size", required = false, defaultValue = "10"
                ) int size
            ) {
        return repository.readByPage(page, size).stream().map(PessoaControllerAdapter::cast).toList();
    }

    @PutMapping("/{id}")
    public PessoaResponse update(@Valid @RequestBody PessoaRequest request, @PathVariable("id") String id) {

        return PessoaControllerAdapter.cast(
                repository.update(
                        id,
                        request.nome(),
                        request.dt_nascimento()
                )
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        repository.delete(id);
    }
}
