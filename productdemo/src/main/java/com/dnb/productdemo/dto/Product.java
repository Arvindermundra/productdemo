package com.dnb.productdemo.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import com.dnb.productdemo.utils.CustomProductIdGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Product {

	

	@Id
	// @NotBlank(message = "account id should not be blank")
	//@GeneratedValue(strategy = GenerationType.UUID)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_seq")
	
	@GenericGenerator(name = "account_seq", strategy = "com.dnb.productdemo.utils.CustomProductIdGenerator",
	parameters =  {@Parameter(name=CustomProductIdGenerator.INCREMENT_PARAM,value="50"),
	@Parameter(name=CustomProductIdGenerator.NUMBER_FORMAT_PARAMETER,value="%05d"),
		@Parameter(name=CustomProductIdGenerator.VALUE_PREFIX_PARAMETER,value="Pro_")}
			)
	private String productId;

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
