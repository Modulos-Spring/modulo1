package org.example.adapter;

import org.example.dto.request.PessoaRequest;
import org.example.dto.response.PessoaResponse;
import org.example.entity.Pessoa;

import java.util.UUID;

public class PessoaControllerAdapter {
    private PessoaControllerAdapter(){}

    public static PessoaResponse cast(Pessoa pessoa) {
        return new PessoaResponse(
                pessoa.id(),
                pessoa.nome(),
                pessoa.dt_nascimento(),
                pessoa.ativo()
        );
    }

    public static Pessoa cast(PessoaRequest request) {
        return new Pessoa(
                UUID.randomUUID().toString(),
                request.nome(),
                request.dt_nascimento(),
                true
        );
    }
}
