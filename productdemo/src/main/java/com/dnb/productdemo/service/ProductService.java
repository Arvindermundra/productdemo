package com.dnb.productdemo.service;

import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Service;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.exceptions.IdNotFoundException;
import com.dnb.productdemo.exceptions.InvalidProductIdException;
@Service
public interface ProductService {
	public Product createProduct(Product product) throws InvalidNameException;
	public boolean productExistsById(String productId);
	public Optional<Product> getProductById(String productId) throws InvalidProductIdException, IdNotFoundException;
	public boolean deleteProductById(String productId) throws IdNotFoundException ;
	public Iterable<Product> getAllProduct() ;
	public Optional<Product> updateProduct(Product product) throws IdNotFoundException, InvalidNameException;

	

}
