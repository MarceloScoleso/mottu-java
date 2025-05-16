package com.mottu.iotmanager.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilialDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String cidade;
}
