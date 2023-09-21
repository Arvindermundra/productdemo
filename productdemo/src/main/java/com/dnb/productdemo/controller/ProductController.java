package com.dnb.productdemo.controller;

import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.exceptions.IdNotFoundException;
import com.dnb.productdemo.exceptions.InvalidProductIdException;
import com.dnb.productdemo.exceptions.ProductNotFoundException;
import com.dnb.productdemo.payload.request.ProductRequest;
import com.dnb.productdemo.service.ProductService;
import com.dnb.productdemo.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product") //map web requests onto specific handler classes and handler methods
public class ProductController {

	@Autowired // automatic dependency injection
	ProductService productService;
	@Autowired //automatic dependency injection
	RequestToEntityMapper mapper;

	@PostMapping("/create")// handle POST requests
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest)
			throws InvalidProductIdException {
		Product product = mapper.getProductEntityObject(productRequest); //Mapping request to product entity
		try {
			return new ResponseEntity(productService.createProduct(product), HttpStatus.CREATED);
		} catch (InvalidNameException e) {
			throw new InvalidProductIdException("Product name should be unique");
		}
	}

	// @GetMapping("/products/{productId}") //handle GET requests
	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId)
			throws IdNotFoundException, InvalidProductIdException {
		Optional<Product> optional = productService.getProductById(productId); //Getting the product by its ID
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get()); //product exists, return it with 200 status code
		} else {
			throw new IdNotFoundException("Product Id not found"); //does not exist, throw exception
		}
	}

	// @GetMapping("/products")
	public ResponseEntity<?> getAllProduct() {
		Iterable<Product> listofProduct = productService.getAllProduct(); //Getting all products
		return ResponseEntity.ok(listofProduct); //Returning all products with a 200 status code
	}

	// @DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId)
			throws IdNotFoundException, InvalidProductIdException {
		Optional<Product> optional = productService.getProductById(productId);
		if (productService.productExistsById(productId)) {
			productService.deleteProductById(productId); //product exists, delete it.
			return (ResponseEntity<?>) ResponseEntity.noContent().build();
		} else {
			throw new InvalidProductIdException("Product Id not valid");
		}
	}
	@PostMapping("/{productId}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("productId") String productId) throws InvalidNameException, IdNotFoundException {
		Optional<Product> updatedProduct;
		if(productService.productExistsById(productId))
			updatedProduct = productService.updateProduct(product); //roduct exists, update it
		else
			throw new IdNotFoundException("Product Id Doesn't exist");
		return new ResponseEntity(updatedProduct, HttpStatus.OK);
	}
}
