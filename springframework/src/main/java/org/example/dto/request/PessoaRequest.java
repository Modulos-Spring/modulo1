package org.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record PessoaRequest(
        @NotBlank(message = "O nome está nulo")
        String nome,
        @NotBlank(message = "A data de nascimento está nula")
        String dt_nascimento
) {
}
