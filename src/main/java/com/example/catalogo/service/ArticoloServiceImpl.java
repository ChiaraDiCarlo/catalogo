package com.example.catalogo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.catalogo.dao.ArticoloDao;
import com.example.catalogo.model.Articolo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticoloServiceImpl implements ArticoloService{
	@Autowired
	private ArticoloDao articoloDao; // istanziamo articoloDao

	@Override
	public List<Articolo> getArticoli() { // recuperiamo tutti gli articoli
		return (List<Articolo>) articoloDao.findAll();
	}

	@Override
	public Articolo getArticoloById(int id) {
		Optional<Articolo> optionalArticolo = articoloDao.findById(id);

		if(optionalArticolo.isPresent()) {
			return optionalArticolo.get(); // ritorna un articolo se è presente in optionalArticolo
		}
		return null; // ritorna null se non è presente nessun articolo
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addToCart(int idArticolo, HttpSession session) {

		Articolo article = getArticoloById(idArticolo); // recuperiamo il prodotto da aggiungere al carrello
		List<Articolo> cart;

		if(session.getAttribute("carrello") != null) { // se esiste un carrello
			cart = (List<Articolo>) session.getAttribute("carrello");

			for (Articolo art : cart)
				if(art.getId() == article.getId())
					return false;
			cart.add(article); // aggiungiamo il prodotto se non è già presente
			session.setAttribute("carrello", cart); // sovrascriviamo l'attributo di session cart
			return true;
		} else { // se non esiste un carrello perché è un primo acquisto
			cart = new ArrayList<>(); // creiamo un nuovo carrello
			cart.add(article); // e ci aggiungiamo un prodotto
			session.setAttribute("carrello", cart);
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Articolo> getCart(HttpSession session) {

		if(session.getAttribute("carrello") != null) // se il carrello non è vuoto
			return (List<Articolo>) session.getAttribute("carrello"); // stampami la lista dei prodotti aggiunti
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeFromCart(int idArticolo, HttpSession session) {
		List<Articolo> cart = (List<Articolo>) session.getAttribute("carrello"); // otteniamo il carrello dalla sessione

		for(Articolo art : cart)
			if(art.getId() == idArticolo) {
				cart.remove(art);
				break;
			}

		if(cart.size() > 0) { // se ho ancora prodotti nel carrello
			session.setAttribute("carrello", cart); // sovrascriviamo
		} else {
			session.removeAttribute("carrello");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getTotalCart(HttpSession session) {
		List<Articolo> cart = (List<Articolo>) session.getAttribute("carrello");

		if(cart != null) { // se il carrello non è vuoto
			double totale = 0; // istanziamo e inizializziamo il totale da 0

			for(Articolo art : cart) {
				totale += art.getPrezzo(); // ci ritorna il totale dei prodotti nel carrello
			}
		}
		return 0;
	}

}
