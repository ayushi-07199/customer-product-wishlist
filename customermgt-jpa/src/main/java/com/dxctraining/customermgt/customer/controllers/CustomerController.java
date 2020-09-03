package com.dxctraining.customermgt.customer.controllers;

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

import com.dxctraining.customermgt.customer.dto.CreateCustomerRequest;
import com.dxctraining.customermgt.customer.dto.CustomerDto;
import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.customer.service.ICustomerService;
import com.dxctraining.customermgt.customer.util.CustomerUtil;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	public ICustomerService service;
	
	@Autowired
	public CustomerUtil customerUtil;
	
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDto create(@RequestBody CreateCustomerRequest requestData) {
		Customer customer = new Customer();
		customer.setName(requestData.getName());
		customer=service.save(customer);
		CustomerDto response=customerUtil.customerdto(customer);
		return response;
	}
	
	
	
	
	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDto findById(@PathVariable("id") Integer id) {
		Customer customer=service.findCustomerById(id);
		CustomerDto response=customerUtil.customerdto(customer);
		return response;
		
			}
	
	@GetMapping ("/name/{name}")
	public List<CustomerDto> findByName (@PathVariable ("name") String name){
		List<Customer>list=service.findByName(name);
		List<CustomerDto>res=CustomerNameList(list);
		return res;
	}
	
	
	public List<CustomerDto> CustomerNameList(List<Customer>list){
		List<CustomerDto>names=new ArrayList<>();
		for(Customer customer:list) {
			CustomerDto res=customerUtil.customerdto(customer);
			names.add(res);
			
		}
		return names;
	}
	
}
