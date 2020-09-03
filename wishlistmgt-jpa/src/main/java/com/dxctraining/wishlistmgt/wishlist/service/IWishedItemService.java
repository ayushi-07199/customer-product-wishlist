package com.dxctraining.wishlistmgt.wishlist.service;


import java.util.List;

import com.dxctraining.wishlistmgt.wishlist.entities.WishedItem;

public interface IWishedItemService {

    WishedItem save(WishedItem wishedItem);
    
    List<WishedItem> findAllById(Integer custId);
    
    List<WishedItem> allWishedItems();

}
