package com.example.catalogo.service;

import com.example.catalogo.dao.UtenteDao;
import com.example.catalogo.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteServiceImpl implements UtenteService{
    @Autowired
    private UtenteDao utenteDao;

    @Override
    public boolean loginUtente(String username, String password, HttpSession session) {
        // solo username e password a differenza di libreria perché non abbiamo tabelle collegate
        Utente utente = utenteDao.findByUsernameAndPassword(username, password);

        if(utente != null) {
            session.setAttribute("utente", utente);
            return true;
        }
        return false;
    }
}
