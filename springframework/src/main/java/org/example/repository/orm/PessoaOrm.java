package org.example.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "pessoa")
public record PessoaOrm(
        @Id
        String id,
        String nome,
        String dt_nascimento,
        boolean ativo
) {
}
