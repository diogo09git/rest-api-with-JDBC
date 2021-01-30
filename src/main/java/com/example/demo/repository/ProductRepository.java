package com.example.demo.repository;

import com.example.demo.domain.Product;

public interface ProductRepository {

	Iterable<Product> findAll();
	
	Product findOne(Integer id);
	
	Product save(Product product);
	
	void delete(Integer id);
}
