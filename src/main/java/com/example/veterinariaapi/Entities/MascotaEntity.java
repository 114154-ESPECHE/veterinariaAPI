package com.example.veterinariaapi.Entities;

import com.example.veterinariaapi.Models.Especie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mascotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MascotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity idCliente;
    @Column
    private String nombre;
    @Column
    private String color;
    @Column
    private int edad;
    @Column
    private Especie especie;
    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private HistoriaClinicaEntity idHistoriaClinica;

}
