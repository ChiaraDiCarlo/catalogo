package com.example.catalogo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.catalogo.model.Articolo;
import com.example.catalogo.service.ArticoloService;


@Controller
@RequestMapping("/dettaglio")
public class DettaglioController {

	@Autowired
	private ArticoloService articoloService;
	
	@GetMapping
		public String getPage(@RequestParam("id")int id, Model model, @RequestParam(name = "add", required=false)String add, HttpSession session) {
			Articolo articolo= articoloService.getArticoloById(id);
			model.addAttribute("articolo", articolo);
			model.addAttribute("add", add);
			model.addAttribute("login", session.getAttribute("utente") != null);
		return "dettaglio";
	}

	@GetMapping("/addToCart")
	public String addToCart(@RequestParam("id") int id, HttpSession session) {
		if(!articoloService.addToCart(id,session))
			return "redirect:/dettaglio?id=" + id + "&add=n";
		return "redirect:/dettaglio?id=" + id + "&add=y";
	}
}
