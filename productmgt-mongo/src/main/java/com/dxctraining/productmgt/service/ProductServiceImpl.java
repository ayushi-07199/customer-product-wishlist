package com.dxctraining.productmgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxctraining.productmgt.dao.IProductDao;
import com.dxctraining.productmgt.entities.Product;
import com.dxctraining.productmgt.exceptions.InvalidArgumentException;
import com.dxctraining.productmgt.exceptions.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductDao dao;


    @Override
    public Product save(Product product) {
    	validate(product);
       product= dao.save(product);
       return product;
    }

    private void validate(Product product) {
		if(product == null) {
			throw new InvalidArgumentException("product should not be null");
		}
		
	}

	@Override
    public Product findById(String id) {
      Optional<Product>optional= dao.findById(id);
      boolean exist=optional.isPresent();
      if(!exist){
          throw new ProductNotFoundException("product not found for id="+id);
      }
      Product product=optional.get();
       return product;
    }

	@Override
	public List<Product> findProductByName(String name) {
		List<Product>list = dao.findByName(name);
		return list;
	}

	@Override
	public List<Product> allProducts() {
		List<Product>list = dao.findAll();
		return list;
	}

    
}
