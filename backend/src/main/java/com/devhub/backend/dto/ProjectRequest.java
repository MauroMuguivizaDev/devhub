package com.devhub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectRequest(

        @NotBlank(message = "Nome do projeto é obrigatório")
        @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
        String name,

        @Size(max = 5000, message = "Descrição deve ter no máximo 5000 caracteres")
        String description,

        @Size(max = 30, message = "Status deve ter no máximo 30 caracteres")
        String status,

        Long userId
) {
}
