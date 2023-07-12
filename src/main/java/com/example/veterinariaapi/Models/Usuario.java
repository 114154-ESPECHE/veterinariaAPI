package com.example.veterinariaapi.Models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private Long dni;
    @NotNull
    private LocalDate fechaNacimiento;
    @NotNull
    private Long telefono;
    @NotNull
    private String direccion;
    @NotNull
    private String email;
    @NotNull
    private LocalDate fechaAlta;
}
