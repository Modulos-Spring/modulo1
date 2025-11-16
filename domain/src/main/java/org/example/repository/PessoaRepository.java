package org.example.repository;

import org.example.entity.Pessoa;

import java.util.List;

public interface PessoaRepository {
    Pessoa create(Pessoa pessoa);

    Pessoa update(String id, String nome, String dt_nascimento);

    Pessoa readById(String id);

    List<Pessoa> readByPage(int page, int size);

    void delete(String id);
}
