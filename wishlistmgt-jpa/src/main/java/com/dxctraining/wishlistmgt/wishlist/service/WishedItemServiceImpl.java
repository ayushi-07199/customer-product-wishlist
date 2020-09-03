package com.dxctraining.wishlistmgt.wishlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.wishlistmgt.exceptions.InvalidArgumentException;
import com.dxctraining.wishlistmgt.wishlist.dao.IWishedItemDao;
import com.dxctraining.wishlistmgt.wishlist.entities.WishedItem;

import java.util.List;
import java.util.Random;

@Transactional
@Service
public class WishedItemServiceImpl implements IWishedItemService{

    @Autowired
    private IWishedItemDao dao;
    
    @Override
    public WishedItem save(WishedItem wishedItem) {
    	String id = generateId();
    	wishedItem.setId(id);
        validate(wishedItem);
        wishedItem=dao.save(wishedItem);
        return wishedItem;
    }

    private String generateId() {
    	
        Random ranNum=new Random();
        int generate=ranNum.nextInt(99999);
        return "Wishlist"+generate+"";
	} 

	private void validate(WishedItem wishedItem) {
		if(wishedItem == null) {
			throw new InvalidArgumentException("WISHEDITEM SHOULD NOT BE NULL");
		}
		
	}


    @Override
    public List<WishedItem> allWishedItems(){
        List<WishedItem>list = dao.findAll();
        return list;
    }

	@Override
	public List<WishedItem> findAllById(Integer custId) {
		validateId(custId);
		List<WishedItem>list = dao.findAllById(custId);
		return list;
	}
	
	private void validateId(Integer custId) {
		if(custId == 0) {
			throw new InvalidArgumentException("id should not be null");
		}
		
	}

}
