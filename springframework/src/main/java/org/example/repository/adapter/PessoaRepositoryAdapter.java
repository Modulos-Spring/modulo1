package org.example.repository.adapter;

import org.example.entity.Pessoa;
import org.example.repository.orm.PessoaOrm;

public class PessoaRepositoryAdapter {
    private PessoaRepositoryAdapter(){}

    public static PessoaOrm cast(Pessoa pessoa) {
        return new PessoaOrm(
                pessoa.id(),
                pessoa.nome(),
                pessoa.dt_nascimento(),
                pessoa.ativo()
        );
    }

    public static Pessoa cast(PessoaOrm orm) {
        return new Pessoa(
                orm.id(),
                orm.nome(),
                orm.dt_nascimento(),
                orm.ativo()
        );
    }
}
