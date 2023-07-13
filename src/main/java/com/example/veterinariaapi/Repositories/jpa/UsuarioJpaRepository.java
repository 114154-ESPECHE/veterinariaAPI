package com.example.veterinariaapi.Repositories.jpa;

import com.example.veterinariaapi.Entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity,Long> {

    UsuarioEntity getUsuarioEntitiesByDni(Long dni);
    UsuarioEntity getUsuarioEntitiesByEmail(String email);

}
