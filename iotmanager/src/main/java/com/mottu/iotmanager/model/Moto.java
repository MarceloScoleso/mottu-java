package com.mottu.iotmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MOTO")
public class Moto {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moto_seq")
@SequenceGenerator(name = "moto_seq", sequenceName = "seq_moto", allocationSize = 1)
@Column(name = "ID_MOTO")
private Long id;

    @NotBlank
    @Column(name = "PLACA")
    private String placa;

    @NotBlank
    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "ANO")
    private Integer ano;

    @NotBlank
    @Column(name = "STATUS_ATUAL")
    private String statusAtual;

    @ManyToOne
    @JoinColumn(name = "ID_FILIAL") 
    private Filial filial;

}