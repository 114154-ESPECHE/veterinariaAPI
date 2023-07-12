package com.example.veterinariaapi.Services;

import com.example.veterinariaapi.Dtos.HistoriaClinica.HistoriaClinicaResponseDTO;
import com.example.veterinariaapi.Dtos.Mascota.NewMascotaRequestDTO;
import com.example.veterinariaapi.Dtos.Mascota.UpdateMascotaRequestDTO;
import com.example.veterinariaapi.Dtos.Mascota.MascotaResponseDTO;
import com.example.veterinariaapi.Models.Mascota;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MascotaService {

    Mascota getMascotaById(Long id);
    MascotaResponseDTO getMascotaResponseDTOById(Long id);

    List<MascotaResponseDTO> getByNombre(String nombre);

    NewMascotaRequestDTO saveMascota(NewMascotaRequestDTO newMascotaRequestDTO);

    UpdateMascotaRequestDTO updateMascota(Long id, UpdateMascotaRequestDTO updateMascotaRequestDTO);
    //UpdateMascotaRequestDTO updateMascota(Long id, UpdateMascotaRequestDTO updateMascotaRequestDTO, HistoriaClinicaResponseDTO historiaClinicaDTO);

    void deleteMascota(Long id);

    HistoriaClinicaResponseDTO getHistoriaClinica(Long idMascota);
}
