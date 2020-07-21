package com.spring.exam.sys.model;

public class CartProductDetails {
	private int cart_id;
	private int ship_id;
	private int product_id;
	private String product_name;
	private String image;
	private String description;
	private String price;
	private int amount;

	// Additional information
	private String prices; // price * amount
	private String totalPrices; // Sum of all prices

	public CartProductDetails() {
		super();
	}

	public CartProductDetails(int cart_id, int ship_id, int product_id, String product_name, String image,
			String description, String price, int amount, String prices, String totalPrices) {
		super();
		this.cart_id = cart_id;
		this.ship_id = ship_id;
		this.product_id = product_id;
		this.product_name = product_name;
		this.image = image;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.prices = prices;
		this.totalPrices = totalPrices;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getShip_id() {
		return ship_id;
	}

	public void setShip_id(int ship_id) {
		this.ship_id = ship_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPrices() {
		return prices;
	}

	public void setPrices(String prices) {
		this.prices = prices;
	}

	public String getTotalPrices() {
		return totalPrices;
	}

	public void setTotalPrices(String totalPrices) {
		this.totalPrices = totalPrices;
	}

	@Override
	public String toString() {
		return "CartProductDetails [cart_id=" + cart_id + ", ship_id=" + ship_id + ", product_id=" + product_id
				+ ", product_name=" + product_name + ", image=" + image + ", description=" + description + ", price="
				+ price + ", amount=" + amount + ", prices=" + prices + ", totalPrices="
				+ totalPrices + "]";
	}

}
