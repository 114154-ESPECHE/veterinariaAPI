package com.example.veterinariaapi.Repositories.jpa;

import com.example.veterinariaapi.Entities.UsuarioEntity;
import com.example.veterinariaapi.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity,Long> {

    Optional<UsuarioEntity> getUsuarioEntitiesByDni(Long dni);

    UsuarioEntity getUsuarioEntitiesByEmail(String email);

    Optional<UsuarioEntity> findUsuarioEntitiesByDniAndPassword(Long dni, String password);

}
