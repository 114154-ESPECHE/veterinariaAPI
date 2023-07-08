package com.example.veterinariaapi.Dtos.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewClienteRequestDTO {
    private String nombre;
    private String apellido;
    private String direccion;
    private int telefono;
    private String email;
}
