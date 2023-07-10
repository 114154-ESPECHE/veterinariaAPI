package com.example.veterinariaapi.Dtos.HistoriaClinica;

import com.example.veterinariaapi.Dtos.Mascota.MascotaResponseDTO;
import com.example.veterinariaapi.Entities.MascotaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriaClinicaRequestDTO {
    private String nombre;
    private LocalDate fecha;
    private String evento;
    private String descripcion;

    public HistoriaClinicaRequestDTO(MascotaResponseDTO mascota,
                                     LocalDate fecha, String evento, String descripcion){
        this.nombre = mascota.getNombre();
        this.fecha = fecha;
        this.evento = evento;
        this.descripcion = descripcion;
    }
}
