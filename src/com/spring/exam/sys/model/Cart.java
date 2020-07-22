package com.spring.exam.sys.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Cart {
	private int cart_id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate cart_date;
	private String city;
	private String country;
	private String zipcode;
	private String username;
	
	private Shipment shipment;
	
	private List<ProductCategory> products;

	public Cart() {
		super();
		this.cart_date = LocalDate.now();
		this.shipment = new Shipment();
	}

	public Cart(int cart_id, LocalDate cart_date, String city, String country, String zipcode, Shipment shipment,
			String username, List<ProductCategory> products) {
		super();
		this.cart_id = cart_id;
		this.cart_date = cart_date;
		this.city = city;
		this.country = country;
		this.zipcode = zipcode;
		this.shipment = shipment;
		this.username = username;
		this.products = products;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public LocalDate getCart_date() {
		return cart_date;
	}

	public void setCart_date(LocalDate cart_date) {
		this.cart_date = cart_date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public String getUsername() {
		return username;
	}

	public void setUser(String username) {
		this.username = username;
	}

	public List<ProductCategory> getProducts() {
		return products;
	}

	public void setProducts(List<ProductCategory> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", cart_date=" + cart_date + ", city=" + city + ", country=" + country
				+ ", zipcode=" + zipcode + ", shipment=" + shipment + ", username=" + username + ", products=" + products.size() + "]";
	}

}
