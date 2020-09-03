package com.dxctraining.customermgt.customer.service;

import java.util.List;

import com.dxctraining.customermgt.customer.entities.Customer;

public interface ICustomerService {
	
	Customer save(Customer customer);
	
	void  remove (Integer id);

	List <Customer> findByName (String name);
	
	Customer findCustomerById(Integer id);
	
}
