package com.example.veterinariaapi.Entities;

import jakarta.persistence.*;
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
    private MascotaEntity idMascota;
    @Column
    private LocalDate fecha;
    @Column
    private String evento;
    @Column
    private String descripcion;

}
