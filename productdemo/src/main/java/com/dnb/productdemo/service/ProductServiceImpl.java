package com.dnb.productdemo.service;

import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.exceptions.IdNotFoundException;
import com.dnb.productdemo.exceptions.InvalidProductIdException;
import com.dnb.productdemo.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) throws InvalidNameException {
		Optional<Product> product2 = productRepository.findByProductName(product.getProductName()); //find product by name
		if (product2.isEmpty()) // doesn't exist
			return productRepository.save(product); // save new product
		else
			throw new InvalidNameException("Name exists-- enter unique name");
	}

	@Override
	public boolean productExistsById(String productId) {
		// TODO Auto-generated method stub
		if (productRepository.existsById(productId)) // check if product exists by Id.
			return true;
		else
			return false;
	}

	@Override
	public Optional<Product> getProductById(String productId) throws InvalidProductIdException, IdNotFoundException {
		// TODO Auto-generated method stub
		if (productRepository.existsById(productId)) {
			Optional<Product> result = productRepository.findById(productId); // find product by ID

			if (result.isPresent()) { // If the product exists
				return result;// get result. else throw exceptions
			} else {
				throw new IdNotFoundException("Product Id Not Found");
			}

		} else
			throw new InvalidProductIdException("Invalid Product Id");
	}

	@Override
	public boolean deleteProductById(String productId) throws IdNotFoundException {
		// TODO Auto-generated method stub

		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId); // delete product by ID
			if (!productRepository.existsById(productId)) {
				return true;
			}
			return false;
		} else {
			throw new IdNotFoundException("Product Id not found");
		}
	}

	@Override
	public Iterable<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll(); // return all products
	}

	@Override
	public Optional<Product> updateProduct(Product product) throws InvalidNameException {

		Optional<Product> product2 = productRepository.findByProductName(product.getProductName()); // find product by name
		if (product2.isEmpty()) {
			productRepository.save(product);// save updated product details
			return Optional.of(product);
		} else {
			throw new InvalidNameException("Prouct name should unique."); // name is not unique, throw an exception
		}
	}

}
