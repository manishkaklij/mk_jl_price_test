package com.jl.pricing.engine.bean;

import java.io.Serializable;

/**
 * 
 * @author Manish Kaklij
 *
 */
public class ColorSwatch implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String color;
	private String rgbColor;
	private String skuId;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getRgbColor() {
		return rgbColor;
	}
	public void setRgbColor(String rgbColor) {
		this.rgbColor = rgbColor;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

}
