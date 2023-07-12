package com.example.veterinariaapi.Dtos.Mascota;

import com.example.veterinariaapi.Dtos.HistoriaClinica.HistoriaClinicaResponseDTO;
import com.example.veterinariaapi.Models.Cliente;
import com.example.veterinariaapi.Models.Especie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MascotaResponseDTO {
    private String nombre;
    private String color;
    private int edad;
    private Especie especie;
    private String nombreCliente;
    private String apellidoCliente;

    public MascotaResponseDTO(String nombre, String color, int edad, Especie especie, Cliente cliente) {
        this.nombre = nombre;
        this.color = color;
        this.edad = edad;
        this.especie = especie;
        if (cliente != null) {
            this.nombreCliente = cliente.getNombre();
            this.apellidoCliente = cliente.getApellido();
        }
    }
}
