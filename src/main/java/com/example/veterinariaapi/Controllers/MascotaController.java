package com.example.veterinariaapi.Controllers;

import com.example.veterinariaapi.Dtos.HistoriaClinica.HistoriaClinicaRequestDTO;
import com.example.veterinariaapi.Dtos.Mascota.NewMascotaRequestDTO;
import com.example.veterinariaapi.Dtos.Mascota.MascotaResponseDTO;
import com.example.veterinariaapi.Dtos.Mascota.UpdateMascotaRequestDTO;
import com.example.veterinariaapi.Entities.MascotaEntity;
import com.example.veterinariaapi.Models.Mascota;
import com.example.veterinariaapi.Services.MascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/mascota-by-id/{id}")
    public ResponseEntity<Mascota> getMascotaById(@PathVariable Long id){
        Mascota mascota = mascotaService.getMascotaById(id);
        return ResponseEntity.ok(mascota);
    }

    @GetMapping("/mascota-ResponseDTO/{nombre}")
    public ResponseEntity<List<MascotaResponseDTO>> getMascotaByNombre(@PathVariable String nombre){
        String nombreUperCase = nombre.substring(0,1).toUpperCase()+ nombre.substring(1);
        List<MascotaResponseDTO> mascotaResponseDTOs = mascotaService.getByNombre(nombreUperCase);
        return ResponseEntity.ok(mascotaResponseDTOs);
    }

    @PostMapping
    public ResponseEntity<MascotaResponseDTO> saveMascota(@Valid @RequestBody NewMascotaRequestDTO newMascotaRequestDTO){
         MascotaResponseDTO mascotaSaved = mascotaService.saveMascota(newMascotaRequestDTO);
         if (mascotaSaved == null){
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet already exists");
         }
         return ResponseEntity.ok(mascotaSaved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateMascotaRequestDTO> updateMascota(@PathVariable Long id, @RequestBody UpdateMascotaRequestDTO updateMascotaRequestDTO,
                                                                 HistoriaClinicaRequestDTO historiaClinicaRequestDTO){
        UpdateMascotaRequestDTO mascotaUpdate = mascotaService.updateMascota(id, updateMascotaRequestDTO, historiaClinicaRequestDTO);
        return ResponseEntity.ok(mascotaUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Long id){
        mascotaService.deleteMascota(id);
        return ResponseEntity.noContent().build();
    }

}
