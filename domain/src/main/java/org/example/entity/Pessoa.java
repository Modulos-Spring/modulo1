package org.example.entity;

public record Pessoa(
        String id,
        String nome,
        String dt_nascimento,
        boolean ativo
) {
}
