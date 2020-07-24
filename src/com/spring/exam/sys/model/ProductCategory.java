package com.spring.exam.sys.model;

public class ProductCategory {
	private int id;
	private String name;
	private int quantity;
	private float price;
	private String description;
	private int isNew;
	private int manufacturer_id;
	private int category_id;
	private String category_name;
	private String image;
	private String image_detail;
	private int percentage;
	private String discount_desc;
	private Details details;

	public ProductCategory() {
		super();
	}

	public ProductCategory(int id, String name, int quantity, float price, String description, int isNew,
			int manufacturer_id, int category_id, String category_name, String image, String image_detail,
			int percentage, String discount_desc, Details details) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.isNew = isNew;
		this.manufacturer_id = manufacturer_id;
		this.category_id = category_id;
		this.category_name = category_name;
		this.image = image;
		this.image_detail = image_detail;
		this.percentage = percentage;
		this.discount_desc = discount_desc;
		this.details = details;
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

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int getManufacturer_id() {
		return manufacturer_id;
	}

	public void setManufacturer_id(int manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public String getDiscount_desc() {
		return discount_desc;
	}

	public void setDiscount_desc(String discount_desc) {
		this.discount_desc = discount_desc;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price
				+ ", description=" + description + ", isNew=" + isNew + ", manufacturer_id=" + manufacturer_id
				+ ", category_id=" + category_id + ", category_name=" + category_name + ", image=" + image
				+ ", image_detail=" + image_detail + ", percentage=" + percentage + ", discount_desc=" + discount_desc
				+ ", details=" + details.toString() + "]";
	}

}
