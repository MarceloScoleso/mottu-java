package com.mottu.iotmanager.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoDTO {
    private Long id;
    private String placa;
    private String modelo;
    private int ano;
    private String statusAtual;
    private Long filialId;
}

