package com.example.veterinariaapi.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @Column
    private Long dni;
    @NotNull
    @NotBlank
    @Column
    private String password;
    @Column
    private LocalDate fechaNacimiento;
    @Column
    private Long telefono;
    @Column
    private String direccion;
    @NotNull
    @NotBlank
    @Column
    private String email;
    @Column
    private LocalDate fechaAlta;
}
