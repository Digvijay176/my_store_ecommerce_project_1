package com.production.mystore.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	@NotNull(message = "should not be null")
	private String name;
	private String description;
	private double actualPrice;
	private String imgUrl;
	private int discount;
	private double discountedPrice;
	@NotNull
	private int quantity;
}
