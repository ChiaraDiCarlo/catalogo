package com.example.catalogo.dao;

import com.example.catalogo.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDao extends CrudRepository<Utente, Integer> {
    Utente findByUsernameAndPassword(String username, String password);
}
