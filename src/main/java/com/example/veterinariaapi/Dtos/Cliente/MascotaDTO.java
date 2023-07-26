package com.example.veterinariaapi.Dtos.Cliente;

import com.example.veterinariaapi.Models.Especie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaDTO {
    private Long id;
    private String nombre;
    private String color;
    private int edad;
    private Especie especie;
}
