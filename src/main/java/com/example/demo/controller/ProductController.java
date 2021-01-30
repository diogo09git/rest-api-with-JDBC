package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductException;
import com.example.demo.repository.ProductRepository;

@RestController
public class ProductController {

	private ProductRepository productRepository;
	
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@GetMapping("/products")
	public List<Product> findAll() {
		
		List<Product> products = new ArrayList<>();
		
		productRepository.findAll().forEach(i -> products.add(i));
		
		return products;
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> findId(@PathVariable Integer id) throws ProductException {
		
		Optional<Product> product = Optional.ofNullable(productRepository.findOne(id));
		
		return product.map(i -> ResponseEntity.ok().body(i))
				.orElse(ResponseEntity.notFound().build());
//				.orElseThrow(() -> new ProductException("product not found"));
		
	}
	
	@PostMapping("/products/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		
		productRepository.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/products/delete/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
		
		Optional<Product> product = Optional.ofNullable(productRepository.findOne(id));
		
		if(product.isPresent()) {
			productRepository.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/products/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product newProduct) {
		
		Optional<Product> oldProduct = Optional.ofNullable((productRepository.findOne(id)));
		
		if(oldProduct.isPresent()) {
			Product product = oldProduct.get();
			product.setName(newProduct.getName());
			productRepository.delete(id);
			productRepository.save(product);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
