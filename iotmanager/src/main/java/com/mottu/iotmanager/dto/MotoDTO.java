package com.mottu.iotmanager.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoDTO {
    private Long id;

    @NotBlank(message = "Placa é obrigatória")
    private String placa;

    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;

    @Min(value = 1900, message = "Ano inválido")
    @Max(value = 2025, message = "Ano inválido")
    private int ano;

    private String statusAtual;

    @NotNull(message = "FilialId é obrigatório")
    private Long filialId;
}
