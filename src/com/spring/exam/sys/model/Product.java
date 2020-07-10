package com.spring.exam.sys.model;

public class Product {
	private int id;
	private String name;
	private int quantity;
	private float price;
	private String description;
	private int manufacturer_id;
	private int type_id;
	private String image;
	private String image_detail;
	private String discount_id;

	public Product() {
		super();
	}

	public Product(int id, String name, int quantity, float price, String description, int manufacturer_id, int type_id, String image,
			String image_detail, String discount_id) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.manufacturer_id = manufacturer_id;
		this.type_id = type_id;
		this.image = image;
		this.image_detail = image_detail;
		this.discount_id = discount_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getManufacturer_id() {
		return manufacturer_id;
	}

	public void setManufacturer_id(int manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}
	
	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage_detail() {
		return image_detail;
	}

	public void setImage_detail(String image_detail) {
		this.image_detail = image_detail;
	}

	public String getDiscount_id() {
		return discount_id;
	}

	public void setDiscount_id(String discount_id) {
		this.discount_id = discount_id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", description="
				+ description + ", manufacturer_id=" + manufacturer_id + ", type_id=" + type_id + ", image=" + image + ", image_detail="
				+ image_detail + ", discount_id=" + discount_id + "]";
	}

}