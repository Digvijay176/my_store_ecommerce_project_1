package com.production.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.production.mystore.entites.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
