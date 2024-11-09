package com.production.mystore.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.production.mystore.entites.Product;
import com.production.mystore.helper.FileHandlingClass;
import com.production.mystore.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // Save product
    @PostMapping("/product")
    public ResponseEntity<?> saveProduct(@RequestParam("image") MultipartFile image,
                                         @RequestParam("productJson") String productJson) {

        if (image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No image file uploaded");
        }

        // Mapping product JSON to product entity
        Product product = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            product = objectMapper.readValue(productJson, Product.class);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format");
        }

        // Handle file upload
        boolean uploadCheck = false;
        try {
            uploadCheck = FileHandlingClass.saveFileInFolder(image);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving file");
        }

        if (uploadCheck) {
            // Set image URL to the product
            String img = image.getOriginalFilename();
            product.setImgUrl(img);

            // Save the product
            Product savedProduct = productService.saveProduct(product);

            if (savedProduct != null) {
                return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save product");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File upload failed");
        }
    }
}
