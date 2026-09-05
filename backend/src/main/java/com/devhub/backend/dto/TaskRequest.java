package com.devhub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequest(

        @NotBlank(message = "Título é obrigatório")
        @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
        String title,

        @Size(max = 5000, message = "Descrição deve ter no máximo 5000 caracteres")
        String description,

        @Size(max = 30, message = "Status deve ter no máximo 30 caracteres")
        String status,

        Long projectId
) {
}
