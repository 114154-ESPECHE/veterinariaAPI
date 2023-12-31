package com.example.veterinariaapi.Dtos.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    private String nombre;
    private String apellido;
    private Long telefono;
    private String email;
}
