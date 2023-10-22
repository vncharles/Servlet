package entity;

public class CartItem {
	private Product product;
	private Integer quantity;
	private Double intoMoney;
	
	

	public CartItem(Product product, Integer quantity, Double intoMoney) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.intoMoney = intoMoney;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public Double getIntoMoney() {
		return intoMoney;
	}

	public void setIntoMoney(Double intoMoney) {
		this.intoMoney = intoMoney;
	}
	
	
	
}
