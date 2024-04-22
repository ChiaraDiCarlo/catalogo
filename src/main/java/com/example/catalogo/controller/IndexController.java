package com.example.catalogo.controller;

import com.example.catalogo.model.Articolo;
import com.example.catalogo.service.ArticoloService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private ArticoloService articoloservice;

	@GetMapping
	public String getPage(Model model, HttpSession session) {
		List<Articolo> articoli = articoloservice.getArticoli();
		model.addAttribute("articoli", articoli);
		model.addAttribute("login", session.getAttribute("utente") != null);
		// true se l'utente è loggato, false se l'utente è sloggato
		return "index";
	}

}
