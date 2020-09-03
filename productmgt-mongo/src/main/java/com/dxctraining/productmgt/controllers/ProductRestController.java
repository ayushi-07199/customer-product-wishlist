package com.dxctraining.productmgt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dxctraining.productmgt.dto.CreateProductRequest;
import com.dxctraining.productmgt.dto.ProductDto;
import com.dxctraining.productmgt.entities.Product;
import com.dxctraining.productmgt.service.IProductService;
import com.dxctraining.productmgt.util.ProductUtil;

@RestController
@RequestMapping("/products")
public class ProductRestController {
	
	@Autowired
	private IProductService service;
	
	@Autowired
	private ProductUtil productUtil; 
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDto addProduct(@RequestBody CreateProductRequest requestData) {
		Product product = new Product(requestData.getName());
		product = service.save(product);
		ProductDto response = productUtil.productDto(product);
		return response;
	}
	
	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDto findProductById(@PathVariable("id")String id) {
		Product product = service.findById(id);
		ProductDto response = productUtil.productDto(product);
		return response;
	}
	
	@GetMapping("/get/product/{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> fetchProductByName(@PathVariable("name")String name){
		List<Product>list = service.findProductByName(name);
		List<ProductDto> response = new ArrayList<>();
		for(Product product:list) {
			ProductDto productDto = productUtil.productDto(product);
			response.add(productDto);
		}
		return response;
		
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> fetchAllProducts(){
		List<Product> list = service.allProducts();
		List<ProductDto>response = new ArrayList<>();
		for(Product product:list) {
			ProductDto dto = productUtil.productDto(product);
			response.add(dto);
		}
		return response;
	}

}
