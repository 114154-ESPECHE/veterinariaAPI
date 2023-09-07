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
    @NotNull(message = "El Apellido no debe ser nulo")
    @NotBlank(message = "El Apellido no debe estar vacio")
    @Column
    private String apellido;
    @NotNull(message = "El Dni no debe ser nulo")
    @NotBlank(message = "El Dni no debe estar vacio")
    @Column
    private Long dni;
    @NotNull(message = "El Password no debe ser nulo")
    @NotBlank(message = "El Password no debe estar vacio")
    @Column
    private String password;
    @Column
    private LocalDate fechaNacimiento;
    @Column
    private Long telefono;
    @Column
    private String direccion;
    @NotNull(message = "El Email no debe ser nulo")
    @NotBlank(message = "El Email no debe estar vacio")
    @Column
    private String email;
    @Column
    private LocalDate fechaAlta;
}
