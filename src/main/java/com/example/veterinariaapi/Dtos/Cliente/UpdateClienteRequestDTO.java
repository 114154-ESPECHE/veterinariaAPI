package com.example.veterinariaapi.Dtos.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClienteRequestDTO {
    private String nombre;
    private String apellido;
    private String direccion;
    private Long telefono;
    private String email;
}
