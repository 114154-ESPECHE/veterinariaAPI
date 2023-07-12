package com.example.veterinariaapi.Models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    @NotNull
    private String nombreUsuario;
    @NotNull
    private String password;
}
