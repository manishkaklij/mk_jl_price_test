package com.jl.pricing.engine.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Manish Kaklij
 *
 */
public class ProductVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String productId;
	private String title;
	private List<ColorSwatchVo> colorSwatches;
	private PriceVo priceVo;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<ColorSwatchVo> getColorSwatches() {
		return colorSwatches;
	}
	public void setColorSwatches(List<ColorSwatchVo> colorSwatches) {
		this.colorSwatches = colorSwatches;
	}
	public PriceVo getPrice() {
		return priceVo;
	}
	public void setPrice(PriceVo priceVo) {
		this.priceVo = priceVo;
	} 

}
