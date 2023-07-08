package com.example.veterinariaapi.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {

    private Long id;
    @NotNull
    private Cliente idCliente;
    @NotNull
    private String nombre;
    @NotNull
    private String color;
    @NotNull
    private int edad;
    @NotNull
    private Especie especie;
}
