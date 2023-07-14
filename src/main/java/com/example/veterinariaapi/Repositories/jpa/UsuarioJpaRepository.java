package com.example.veterinariaapi.Repositories.jpa;

import com.example.veterinariaapi.Entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity,Long> {

    Optional<UsuarioEntity> getUsuarioEntitiesByDni(Long dni);

    UsuarioEntity getUsuarioEntitiesByEmail(String email);

}
