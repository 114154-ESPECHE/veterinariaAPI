package com.example.veterinariaapi.Dtos.Login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBody {
    @NotNull(message = "Username can't by null")
    private Long  dni;

    @NotNull(message = "Password can't by null")
    private String password;
}
