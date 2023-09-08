package com.example.veterinariaapi.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El Nombre no debe ser nulo")
    @NotBlank(message = "El Nombre no debe estar vacio")
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private Long dni;
    @Column
    private String password;
    @Column
    private LocalDate fechaNacimiento;
    @Column
    private Long telefono;
    @Column
    private String direccion;
    @Column
    private String email;
    @Column
    private LocalDate fechaAlta;
}
