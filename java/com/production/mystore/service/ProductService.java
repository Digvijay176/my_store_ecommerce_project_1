package com.production.mystore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.mystore.entites.Product;
import com.production.mystore.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	//add product
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	//get product by id
	public Optional<Product> getProductById(int id){
		return productRepository.findById(id);	
	}
	
	//get all product
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
}
