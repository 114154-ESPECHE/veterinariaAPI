package com.example.veterinariaapi.Repositories.jpa;

import com.example.veterinariaapi.Entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findClienteEntitiesById(Long id);
    Optional<ClienteEntity> findClienteEntitiesByTelefono(int telefono);
    Optional<ClienteEntity> findClienteEntitiesByEmail(String email);
    Optional<ClienteEntity> findClienteEntitiesByNombreAndTelefono(String nombre, int telefono);

    Optional<ClienteEntity> findClienteEntitiesByNombreAndApellido(String nombre, String apellido);

}
