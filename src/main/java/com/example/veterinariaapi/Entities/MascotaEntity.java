package com.example.veterinariaapi.Entities;

import com.example.veterinariaapi.Models.Especie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoriaClinicaEntity> HistoriaClinicaList;

}
