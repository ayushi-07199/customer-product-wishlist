package com.dxctraining.wishlistmgt.wishlist.util;

import org.springframework.stereotype.Component;

import com.dxctraining.wishlistmgt.wishlist.dto.WishedItemDto;
import com.dxctraining.wishlistmgt.wishlist.entities.WishedItem;

@Component
public class WishedItemUtil {


    public WishedItemDto wishedItemDto(WishedItem wishedItem, Integer custId, String custName, String productId, String productName){
        WishedItemDto dto=new WishedItemDto(wishedItem.getId());
        dto.setCustId(custId);
        dto.setCustName(custName);
        dto.setProductId(productId);
        dto.setProductName(productName);
        return dto;
    }

}
