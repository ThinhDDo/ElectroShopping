package com.spring.exam.sys.model;

public class Details {
	private int id;
	private String screen;
	private String size;
	private String resolution;
	private String os;
	private String camera;
	private String chipset;
	private String hardward;

	public Details() {
		super();
	}

	public Details(int id, String screen, String size, String resolution, String os, String camera, String chipset,
			String hardward) {
		super();
		this.id = id;
		this.screen = screen;
		this.size = size;
		this.resolution = resolution;
		this.os = os;
		this.camera = camera;
		this.chipset = chipset;
		this.hardward = hardward;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getChipset() {
		return chipset;
	}

	public void setChipset(String chipset) {
		this.chipset = chipset;
	}

	public String getHardward() {
		return hardward;
	}

	public void setHardward(String hardward) {
		this.hardward = hardward;
	}

	@Override
	public String toString() {
		return "Details [id=" + id + ", screen=" + screen + ", size=" + size + ", resolution=" + resolution + ", os="
				+ os + ", camera=" + camera + ", chipset=" + chipset + ", hardward=" + hardward + "]";
	}

}
