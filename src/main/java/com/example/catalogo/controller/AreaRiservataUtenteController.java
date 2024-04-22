package com.example.catalogo.controller;

import com.example.catalogo.model.Utente;
import com.example.catalogo.service.ArticoloService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/areariservata")
public class AreaRiservataUtenteController {

    @Autowired
    private ArticoloService articoloService;

    @GetMapping // Metodo per ottenere la pagina riservata dell'utente
    public String getPage (HttpSession session,
                           Model model) {

        if(session.getAttribute("utente") == null) { // se non è autenticato
            return "redirect:/loginutente"; // ritorna al login con la scritta credenziali errate
        }

        Utente utente = (Utente) session.getAttribute("utente");
        // altrimenti:
        model.addAttribute("utente", utente);
        model.addAttribute("carrello", articoloService.getCart(session));
        model.addAttribute("totale", articoloService.getTotalCart(session));
        return "areariservatautente";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("utente");
        return "redirect:/"; // indexController
    }

    @GetMapping("/remove")
    public String removeArticle(@RequestParam("id") int idArticle, HttpSession session) {
        articoloService.removeFromCart(idArticle, session); // invoca il metodo da articoloService per rimuovere il prodotto dal carrello
        return "redirect:/areariservata"; // e reindirizza l'utente all'area riservata
    }

}
