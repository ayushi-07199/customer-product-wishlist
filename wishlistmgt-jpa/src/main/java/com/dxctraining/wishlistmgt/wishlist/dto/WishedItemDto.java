package com.dxctraining.wishlistmgt.wishlist.dto;

public class WishedItemDto {

    private String id;

    private Integer custId;
    
    private String productId;
    
    private String custName;
    
    private String productName;

    public WishedItemDto(){

    }

    public WishedItemDto(String id){
        this.id=id;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
