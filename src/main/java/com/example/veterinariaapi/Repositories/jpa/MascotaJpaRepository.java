package com.example.veterinariaapi.Repositories.jpa;

import com.example.veterinariaapi.Entities.MascotaEntity;
import com.example.veterinariaapi.Models.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    //MascotaEntity getMascotaEntityByIdCliente
    @Query("SELECT m FROM MascotaEntity m WHERE m.nombre = :nombreMascota AND m.idCliente.dni = :dniCliente")
    MascotaEntity buscarPorNombreYDniCliente(@Param("nombreMascota") String nombreMascota, @Param("dniCliente") Long dniCliente);
}
