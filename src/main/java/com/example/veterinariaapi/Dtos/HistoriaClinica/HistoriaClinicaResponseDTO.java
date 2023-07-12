package com.example.veterinariaapi.Dtos.HistoriaClinica;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriaClinicaResponseDTO {
    private String nombre;
    private LocalDate fecha;
    private String evento;
    private String descripcion;

}
