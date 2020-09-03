package com.dxctraining.customermgt.customer.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxctraining.customermgt.customer.dao.ICustomerDao;
import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.exception.CustomernotFoundException;

@Service
@Transactional

public class CustomerServiceImplement implements ICustomerService {
	
	@Autowired
	private ICustomerDao dao;
	
	
	@Override
	 public Customer save (Customer customer) {
		customer =dao.save(customer);
		return customer;
				
	}
	
	@Override
	public void remove(Integer id) {
		dao.deleteById(id);
	}
	
	@Override
	public List<Customer> findByName(String name) {
		List<Customer> list = dao.findByName(name);
		return list;
	}
		
		
   @Override
   public Customer findCustomerById(Integer id){
	  Optional<Customer>optional =dao.findById(id);
	  if(!optional.isPresent()) {
		  throw new CustomernotFoundException("THERE IS NO CUSTOMER FOR THIS ID=" +id);
		  }
	  
	  Customer customer=optional.get();
	  return  customer;
	  
   
	}

	
	

   
}
