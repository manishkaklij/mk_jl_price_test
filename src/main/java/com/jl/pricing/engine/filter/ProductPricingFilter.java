package com.jl.pricing.engine.filter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jl.pricing.engine.vo.ProductVo;

/**
 * 
 * @author Manish Kaklij
 *
 */
@Component
public class ProductPricingFilter implements ProductFilter {

	@Override
	public List<ProductVo> filter(List<ProductVo> productVos) {
		return productVos.stream()
				.filter(product -> product.getPrice().getPriceReduction() > 0d)
				.collect(Collectors.toList());
	}

}
