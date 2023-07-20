package com.example.veterinariaapi.Dtos.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
    private Long dni;
    private LocalDate fechaNacimiento;
    private Long telefono;
    private String direccion;
    private String email;
}
