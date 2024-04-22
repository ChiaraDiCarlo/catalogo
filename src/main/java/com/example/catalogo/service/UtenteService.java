package com.example.catalogo.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface UtenteService {
    boolean loginUtente(String username, String password, HttpSession session);
}
