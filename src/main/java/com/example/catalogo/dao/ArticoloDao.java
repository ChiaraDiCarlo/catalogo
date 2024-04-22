package com.example.catalogo.dao;

import org.springframework.data.repository.CrudRepository;
import com.example.catalogo.model.Articolo;

public interface ArticoloDao extends CrudRepository<Articolo, Integer>
{

}
