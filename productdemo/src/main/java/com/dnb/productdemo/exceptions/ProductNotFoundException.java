package com.dnb.productdemo.exceptions;

public class ProductNotFoundException extends Exception {

	public ProductNotFoundException(String msg) {
		super(msg);
	}
	public String toString() {
		return super.toString() + super.getMessage();
	}

}
