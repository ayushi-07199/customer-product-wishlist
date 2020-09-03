package com.dxctraining.customermgt.customer.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dxctraining.customermgt.customer.dto.CustomerDto;
import com.dxctraining.customermgt.customer.entities.Customer;

@Component
public class CustomerUtil {
	
	public CustomerDto customerdto(Customer customer) {
		CustomerDto dto= new CustomerDto(customer.getId(),customer.getName());
		return dto;
	}

	
	
	}
