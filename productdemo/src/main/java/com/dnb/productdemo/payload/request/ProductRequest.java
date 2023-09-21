package com.dnb.productdemo.payload.request;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {
	
	@Column(unique = true)
	@NotBlank(message = "product name should not be blank")
	private String productName;
	@Min(value= 0, message = "value should not be negative")
	private float productPrice;
	@NotBlank(message = "product Description should not be empty")
	private String productDescription;
	@NotNull(message = "product expiry should not be empty")
	private LocalDate productExpiry;
	@NotBlank(message = "product Category should not be blank")
	private String productCategory;
	

}
