package com.example.veterinariaapi.Services.Impl;

import com.example.veterinariaapi.Dtos.HistoriaClinica.HistoriaClinicaResponseDTO;
import com.example.veterinariaapi.Dtos.Mascota.NewMascotaRequestDTO;
import com.example.veterinariaapi.Dtos.Mascota.UpdateMascotaRequestDTO;
import com.example.veterinariaapi.Dtos.Mascota.MascotaResponseDTO;
import com.example.veterinariaapi.Entities.ClienteEntity;
import com.example.veterinariaapi.Entities.HistoriaClinicaEntity;
import com.example.veterinariaapi.Entities.MascotaEntity;
import com.example.veterinariaapi.Models.Mascota;
import com.example.veterinariaapi.Repositories.jpa.ClienteJpaRepository;
import com.example.veterinariaapi.Repositories.jpa.MascotaJpaRepository;
import com.example.veterinariaapi.Services.MascotaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaServiceImpl implements MascotaService {
    @Autowired
    private MascotaJpaRepository mascotaJpaRepository;
    @Autowired
    private ClienteJpaRepository clienteJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Mascota getMascotaById(Long id) {
        MascotaEntity mascotaEntity = mascotaJpaRepository.getMascotaEntitiesById(id);
        if (Objects.isNull(mascotaEntity)){
            throw new EntityNotFoundException();
        }
        Mascota mascota = modelMapper.map(mascotaEntity, Mascota.class);
        return mascota;
    }

    @Override
    public MascotaResponseDTO getMascotaResponseDTOById(Long id) {
        MascotaEntity  mascotaEntity = mascotaJpaRepository.getReferenceById(id);
        if (Objects.isNull(mascotaEntity)){
            throw new EntityNotFoundException();
        }
        MascotaResponseDTO mascotaResponseDTO = modelMapper.map(mascotaEntity,MascotaResponseDTO.class);
        return mascotaResponseDTO;
    }

    @Override
    public List<MascotaResponseDTO> getByNombre(String nombre) {
        List<MascotaEntity> mascotaEntities = mascotaJpaRepository.findMascotaEntitiesByNombre(nombre);
        if (!mascotaEntities.isEmpty()){
            List<MascotaResponseDTO> mascotaResponseDTOs = mascotaEntities.stream()
                    .map(entity -> modelMapper.map(entity, MascotaResponseDTO.class))
                    .collect(Collectors.toList());
            return mascotaResponseDTOs;
        }else{
            throw new EntityNotFoundException();
        }
    }


    @Override
    public NewMascotaRequestDTO saveMascota(NewMascotaRequestDTO newMascotaRequestDTO) {
        ClienteEntity cliente = clienteJpaRepository.findClienteEntitiesByDni(newMascotaRequestDTO.getDni())
                .orElseThrow(() -> new EntityNotFoundException("Cliente not found"));

        MascotaEntity mascotaEntity = new MascotaEntity();
        mascotaEntity.setIdCliente(cliente);
        mascotaEntity.setNombre(newMascotaRequestDTO.getNombre());
        mascotaEntity.setColor(newMascotaRequestDTO.getColor());
        mascotaEntity.setEdad(newMascotaRequestDTO.getEdad());
        mascotaEntity.setEspecie(newMascotaRequestDTO.getEspecie());

        mascotaEntity = mascotaJpaRepository.save(mascotaEntity);

        return modelMapper.map(mascotaEntity, NewMascotaRequestDTO.class);
    }

    @Override
    public UpdateMascotaRequestDTO updateMascota(Long id, UpdateMascotaRequestDTO updateMascotaRequestDTO) {
        MascotaEntity mascotaEntity = mascotaJpaRepository.getReferenceById(id);
        if (Objects.isNull(mascotaEntity.getNombre())){
            throw new RuntimeException("Mascota no encontrado");
        }
        mascotaEntity.setNombre(updateMascotaRequestDTO.getNombre());
        mascotaEntity.setColor(updateMascotaRequestDTO.getColor());
        mascotaEntity.setEdad(updateMascotaRequestDTO.getEdad());
        mascotaEntity.setEspecie(updateMascotaRequestDTO.getEspecie());

        mascotaJpaRepository.save(mascotaEntity);
        return  modelMapper.map(mascotaEntity, UpdateMascotaRequestDTO.class);
    }

    @Override
    public void deleteMascota(Long id) {
        MascotaEntity mascotaEntity = mascotaJpaRepository.getReferenceById(id);
        if (Objects.isNull(mascotaEntity.getNombre())){
            throw new RuntimeException("Mascota no encontrado");
        }
        mascotaJpaRepository.delete(mascotaEntity);
    }

    @Override
    public List<HistoriaClinicaResponseDTO> getHistoriaClinica(Long idMascota) {
        String nombreMascota = getNombreMascotaById(idMascota);
        MascotaEntity mascota = mascotaJpaRepository.getMascotaEntitiesById(idMascota);
        if (Objects.isNull(mascota.getId())){
            throw new RuntimeException("Mascota no encontrada");
        }
        List<HistoriaClinicaEntity> historiaClinicaEntityList = mascota.getHistoriaClinicaList();

        List<HistoriaClinicaResponseDTO> historiaClinicaResponseDTOList = historiaClinicaEntityList.stream()
                .map(entity -> {
                    HistoriaClinicaResponseDTO dto = modelMapper.map(entity, HistoriaClinicaResponseDTO.class);
                    dto.setNombre(nombreMascota);
                    return dto;
                })
                .collect(Collectors.toList());

        return historiaClinicaResponseDTOList;
    }

    @Override
    public String getNombreMascotaById(Long idMascota) {
        MascotaEntity mascota = mascotaJpaRepository.getMascotaEntitiesById(idMascota);
        return mascota.getNombre();
    }

    @Override
    public MascotaEntity getMascotaByDniCliente(Long dni) {
        return null;
    }
    @Override
    public MascotaResponseDTO buscarMascotaPorNombreYDniCliente(String nombreMascota, Long dniCliente) {
        MascotaEntity mascota = mascotaJpaRepository.buscarPorNombreYDniCliente(nombreMascota,dniCliente);
        if (Objects.isNull(mascota)){
            throw new RuntimeException("Mascota no encontrada para el dni " + dniCliente + "del cliente");
        }
        return modelMapper.map(mascota, MascotaResponseDTO.class);
    }


}
