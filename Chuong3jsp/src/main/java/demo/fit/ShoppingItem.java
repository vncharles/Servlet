package demo.fit;

import java.io.Serializable;

public class ShoppingItem implements Serializable{
	private static final long serialVersionUID = -100L;
	private Product product;
	private Integer quantity;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ShoppingItem(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	
	

}
