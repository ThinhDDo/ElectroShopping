package com.spring.exam.sys.model;

public class Shipment {
	private int ship_id;
	private String address;
	private String notes;

	public Shipment() {
		super();
		this.address = "";
		this.notes = "";
	}

	public Shipment(int ship_id, String address, String notes) {
		super();
		this.ship_id = ship_id;
		this.address = address;
		this.notes = notes;
	}

	public int getShip_id() {
		return ship_id;
	}

	public void setShip_id(int ship_id) {
		this.ship_id = ship_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Shipment [ship_id=" + ship_id + ", address=" + address + ", notes=" + notes + "]";
	}

}
