package com.example.veterinariaapi.Services;

import com.example.veterinariaapi.Models.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Usuario login(Long dni, String password);
}
