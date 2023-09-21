package com.dnb.productdemo.exceptions;

public class InvalidProductIdException extends Exception {
	public InvalidProductIdException(String msg) {
		super(msg);
	}

	public String toString() {
		return super.toString() + super.getMessage();
	}
}
