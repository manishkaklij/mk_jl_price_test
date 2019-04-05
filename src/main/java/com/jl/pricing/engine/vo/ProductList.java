package com.jl.pricing.engine.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Manish Kaklij
 *
 */
public class ProductList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<ProductVo> productVos;

	public List<ProductVo> getProducts() {
		return productVos;
	}

	public void setProducts(List<ProductVo> productVos) {
		this.productVos = productVos;
	}

}
