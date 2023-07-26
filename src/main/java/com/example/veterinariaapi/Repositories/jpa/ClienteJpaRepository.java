package com.example.veterinariaapi.Repositories.jpa;

import com.example.veterinariaapi.Entities.ClienteEntity;
import com.example.veterinariaapi.Entities.MascotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findClienteEntitiesById(Long id);
    Optional<ClienteEntity> findClienteEntitiesByTelefono(Long telefono);
    Optional<ClienteEntity> findClienteEntitiesByEmail(String email);
    Optional<ClienteEntity> findClienteEntitiesByNombreAndTelefono(String nombre, Long telefono);

    Optional<ClienteEntity> findClienteEntitiesByDni(Long dni);

    Optional<ClienteEntity> findClienteEntitiesByNombreAndApellido(String nombre, String apellido);

    @Query("SELECT mascota FROM MascotaEntity mascota WHERE mascota.idCliente.dni = :dni")
    List<MascotaEntity> findMascotasByClienteDni(@Param("dni") Long dni);


}
