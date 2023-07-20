package com.example.veterinariaapi.Repositories.jpa;

import com.example.veterinariaapi.Entities.MascotaEntity;
import com.example.veterinariaapi.Models.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MascotaJpaRepository extends JpaRepository<MascotaEntity, Long> {
    Optional<MascotaEntity> findMascotaEntitiesByNombreAndEspecie(String nombre, Especie especie);
    List<MascotaEntity> findMascotaEntitiesByNombre(String nombre);
    List<MascotaEntity> findMascotaEntitiesById(Long id);
    Optional<MascotaEntity> findAllByEspecie(Especie especie);

    MascotaEntity getMascotaEntitiesById(Long id);

}
