package com.spring.exam.sys.model;

public class Manufacturer {
	private int manufacturer_id;
	private String manufacturer_name;
	private String image;

	public Manufacturer() {
		super();
	}

	public Manufacturer(int manufacturer_id, String manufacturer_name, String image) {
		super();
		this.manufacturer_id = manufacturer_id;
		this.manufacturer_name = manufacturer_name;
		this.image = image;
	}

	public int getManufacturer_id() {
		return manufacturer_id;
	}

	public void setManufacturer_id(int manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}

	public String getManufacturer_name() {
		return manufacturer_name;
	}

	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Manufacturer [manufacturer_id=" + manufacturer_id + ", manufacturer_name=" + manufacturer_name
				+ ", image=" + image + "]";
	}

}
