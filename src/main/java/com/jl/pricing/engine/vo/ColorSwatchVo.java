package com.jl.pricing.engine.vo;

import java.io.Serializable;

/**
 * 
 * @author Manish Kaklij
 *
 */
public class ColorSwatchVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String color;
	private String basicColor;
	private String skuId;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBasicColor() {
		return basicColor;
	}
	public void setBasicColor(String basicColor) {
		this.basicColor = basicColor;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

}
