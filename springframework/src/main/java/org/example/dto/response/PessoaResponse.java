package org.example.dto.response;

public record PessoaResponse(
        String id,
        String nome,
        String dt_nascimento,
        boolean ativo
) {
}
