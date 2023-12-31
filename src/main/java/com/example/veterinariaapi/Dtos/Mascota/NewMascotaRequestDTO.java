package com.example.veterinariaapi.Dtos.Mascota;

import com.example.veterinariaapi.Entities.ClienteEntity;
import com.example.veterinariaapi.Models.Cliente;
import com.example.veterinariaapi.Models.Especie;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMascotaRequestDTO {
    private Long dni;
    private String nombre;
    private String color;
    private int edad;
    private Especie especie;
}
