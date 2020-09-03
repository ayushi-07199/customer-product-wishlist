package com.dxctraining.wishlistmgt.wishlist.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.dxctraining.wishlistmgt.exceptions.InvalidArgumentException;
import com.dxctraining.wishlistmgt.wishlist.entities.WishedItem;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(WishedItemServiceImpl.class)
class WishedItemServiceImplTest {
	
	@Autowired
	private IWishedItemService service;
	
	@Test
	public void testAdd_1() {
		Executable executable =()->service.save(null);
		Assertions.assertThrows(InvalidArgumentException.class, executable);
	}
	
	@Test
	public void testAdd_2() {
		int custId = 1;
		String productId = "a1";
		WishedItem wishedItem = new WishedItem(custId,productId);
		wishedItem = service.save(wishedItem);
		List<WishedItem>list = new ArrayList<>();
		list.add(wishedItem);
		WishedItem fetched = list.get(0);
		Assertions.assertEquals(1, list.size());
		Assertions.assertEquals(custId, fetched.getCustId());
		Assertions.assertEquals(productId, fetched.getProductId());
	}

}
