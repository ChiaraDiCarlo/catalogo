package com.example.catalogo.service;

import java.util.List;

import com.example.catalogo.model.Articolo;
import jakarta.servlet.http.HttpSession;


public interface ArticoloService {

	List<Articolo> getArticoli(); // tutti gli articoli disponibili

	Articolo getArticoloById(int id); // restituisce il prodotto corrispondente all'id cercato

	boolean addToCart(int idArticolo, HttpSession session); // aggiunge il prodotto al carrello

	List<Articolo> getCart(HttpSession session); // restituisce la lista di prodotti presente nel carrello

	void removeFromCart(int idArticolo, HttpSession session); // rimuove il prodotto dal carrello

	double getTotalCart(HttpSession session); // calcola e restituisce il totale del prezzo dei prodotti nel carrello
}
