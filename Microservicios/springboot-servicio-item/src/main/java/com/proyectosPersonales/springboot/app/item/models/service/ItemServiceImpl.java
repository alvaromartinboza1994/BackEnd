package com.proyectosPersonales.springboot.app.item.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyectosPersonales.springboot.app.item.models.Item;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clienteRestprobando;
	
	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

}
