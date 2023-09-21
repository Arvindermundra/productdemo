package com.dnb.productdemo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.productdemo.dto.Product;
@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
	public Optional<Product> findByProductName(String productName);

}
