package com.dxctraining.wishlistmgt.wishlist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.dxctraining.wishlistmgt.wishlist.dto.CreateWishedItemRequest;
import com.dxctraining.wishlistmgt.wishlist.dto.CustomerDto;
import com.dxctraining.wishlistmgt.wishlist.dto.ProductDto;
import com.dxctraining.wishlistmgt.wishlist.dto.WishedItemDto;
import com.dxctraining.wishlistmgt.wishlist.entities.WishedItem;
import com.dxctraining.wishlistmgt.wishlist.service.IWishedItemService;
import com.dxctraining.wishlistmgt.wishlist.util.WishedItemUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlists")
public class WishedItemRestController {

    @Autowired
    private IWishedItemService service;

    @Autowired
    private WishedItemUtil wishedItemUtil;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public WishedItemDto addWishlist(@RequestBody CreateWishedItemRequest requestData) {
		WishedItem wishedItem = new WishedItem(requestData.getCustId(), requestData.getProductId());
		wishedItem = service.save(wishedItem);
		CustomerDto customerDto = findCustomerDetailsByCustId(requestData.getCustId());
		customerDto.setCustId(requestData.getCustId());
		ProductDto productDto = findProductDetailsByProductId(requestData.getProductId());
		WishedItemDto response = wishedItemUtil.wishedItemDto(wishedItem,customerDto.getCustId(),customerDto.getName(),productDto.getId(),productDto.getName());
		return response;
	}
    
    @GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<WishedItemDto> findAllWishedItemsById(@PathVariable("id")Integer custId) {
		List<WishedItem>list = service.findAllById(custId);
		List<WishedItemDto>response = new ArrayList<>();
		for(WishedItem wishedItem:list) {
			String productId = wishedItem.getProductId();
        	ProductDto productDto = findProductDetailsByProductId(productId);
        	Integer customerId = wishedItem.getCustId();
        	CustomerDto customerDto = findCustomerDetailsByCustId(customerId);
			WishedItemDto dto = wishedItemUtil.wishedItemDto(wishedItem,customerId,customerDto.getName(),productId, productDto.getName());
			response.add(dto);
		}
		return response;
	}

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WishedItemDto> fetchAll() {
        List<WishedItem> list = service.allWishedItems();
        List<WishedItemDto>response=new ArrayList<>();
        for (WishedItem wishedItem:list){
        	String productId = wishedItem.getProductId();
        	ProductDto productDto = findProductDetailsByProductId(productId);
        	Integer custId = wishedItem.getCustId();
        	CustomerDto customerDto = findCustomerDetailsByCustId(custId);
            WishedItemDto dto=wishedItemUtil.wishedItemDto(wishedItem,custId,customerDto.getName(),productId, productDto.getName());
            response.add(dto);
        }
        return response;
    }
    
    public CustomerDto findCustomerDetailsByCustId(Integer custId) {
    	String url = "http://localhost:8585/customers/get/"+custId;
    	CustomerDto dto = restTemplate.getForObject(url, CustomerDto.class);
		return dto;
    }
    
    public ProductDto findProductDetailsByProductId(String productId) {
    	String url = "http://localhost:8586/products/get/"+productId;
    	ProductDto dto = restTemplate.getForObject(url, ProductDto.class);
		return dto;
    }

}




