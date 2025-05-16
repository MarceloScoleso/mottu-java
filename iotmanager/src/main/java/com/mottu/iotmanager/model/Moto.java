package com.mottu.iotmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String placa;

    @NotBlank
    private String modelo;

    @Min(2000)
    private int ano;

    @NotBlank
    private String statusAtual;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;
}
