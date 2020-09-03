package com.dxctraining.customermgt.customer.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.exception.*;
import com.dxctraining.customermgt.exception.CustomernotFoundException;
import com.dxctraining.customermgt.exception.InvalidArgumentException;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({ CustomerServiceImplement.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerServiceImplTest {

	@Autowired
	private ICustomerService service;

	@Autowired
	private EntityManager em;

	@Test
	public void testAdd_1() {
		Executable execute=()->service.save(null);
		Assertions.assertThrows(InvalidArgumentException.class, execute);
	}
	
	@Test
	public void testAdd_2() {
		String name = "Ayushi";
		Customer customer = new Customer(name);
		customer = service.save(customer);
		Customer fetched = service.findCustomerById(customer.getId());
		Assertions.assertEquals(customer.getId(),fetched.getId());
		Assertions.assertEquals(name,fetched.getName());
	}
	
	@Test
	public void testFindById_1() {
		Executable executable=()->service.findCustomerById(0);
		Assertions.assertThrows(CustomernotFoundException.class, executable);
	}
	
	@Test
	public void testFindById_2() {
		String name = "Ansh";
		Customer customer = new Customer(name);
		customer = service.save(customer);
		Customer fetched = service.findCustomerById(customer.getId());
		Assertions.assertEquals(customer.getId(), fetched.getId());
	}

}
