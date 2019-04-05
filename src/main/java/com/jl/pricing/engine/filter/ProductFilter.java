package com.jl.pricing.engine.filter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jl.pricing.engine.vo.ProductVo;

/**
 * 
 * @author Manish Kaklij
 *
 */
@Component
public interface ProductFilter {
	
	public List<ProductVo> filter(List<ProductVo> productVos);

}
