package com.example.catalogo.controller;

import com.example.catalogo.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/loginutente")
public class LoginUtenteController {

   @Autowired
   private UtenteService utenteService;

   @GetMapping
    public String getPage(HttpSession session, // le credenziali sono errate non è da subito visibile
                          @RequestParam(name = "error", required = false) String error, // in caso il form lo richiami
                          Model model) {
       if (session.getAttribute("utente") != null)  // in caso l'utente non inserisce nulla
           return "redirect:/areariservata";
       model.addAttribute("error", error);
       return "loginutente";
   }

   @PostMapping
    public String form(@RequestParam("username")String username,
                       @RequestParam("password")String password,
                       HttpSession session) {
       if (!utenteService.loginUtente(username, password, session)) {
           return "redirect:/loginutente?error"; // in caso le credenziali sono errate
       }
       return "redirect:/areariservata"; // in caso le credenziali sono esatte
   }
}
