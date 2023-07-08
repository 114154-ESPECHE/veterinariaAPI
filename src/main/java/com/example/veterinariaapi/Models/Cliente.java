package com.example.veterinariaapi.Models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Long id;
    @NotNull(message = "Name can't be null")
    private String nombre;
    @NotNull(message = "Last name can't be null")
    private String apellido;
    private String direccion;
    @NotNull(message = "Phonenumber name can't be null")
    private int telefono;
    @NotNull(message = "Email name can't be null")
    private String email;


}
