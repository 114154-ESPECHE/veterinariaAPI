package com.example.veterinariaapi.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "HISTORIAMASCOTAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriaClinicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_mascota")
    private MascotaEntity mascota;
    @Column
    private LocalDate fecha;
    @NotNull
    @NotBlank
    @Column
    private String evento;
    @NotNull
    @NotBlank
    @Column
    private String descripcion;

}
