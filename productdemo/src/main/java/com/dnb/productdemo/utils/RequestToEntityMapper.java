package com.dnb.productdemo.utils;

import org.springframework.stereotype.Component;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.payload.request.ProductRequest;

import jakarta.validation.Valid;

@Component
public class RequestToEntityMapper {

	public Product getProductEntityObject(@Valid ProductRequest productRequest) {
		Product product = new Product();
		// TODO Auto-generated method stub
		product.setProductPrice(productRequest.getProductPrice());
		product.setProductName(productRequest.getProductName());
		product.setProductExpiry(productRequest.getProductExpiry());
		product.setProductDescription(productRequest.getProductDescription());
		product.setProductCategory(productRequest.getProductCategory());
		return product;
	}

}
