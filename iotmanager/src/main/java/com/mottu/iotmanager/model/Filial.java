package com.mottu.iotmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FILIAL") 
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filial_seq")
    @SequenceGenerator(name = "filial_seq", sequenceName = "SEQ_FILIAL", allocationSize = 1)
    @Column(name = "ID_FILIAL") 
    private Long id;

    @NotBlank
    @Column(name = "NOME")
    private String nome;

    @NotBlank
    @Column(name = "ENDERECO")
    private String endereco;

    @NotBlank
    @Column(name = "CIDADE")
    private String cidade;
}