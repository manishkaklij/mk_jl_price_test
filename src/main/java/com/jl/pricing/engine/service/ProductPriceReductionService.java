package com.jl.pricing.engine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.jl.pricing.engine.bean.Product;
import com.jl.pricing.engine.filter.ProductFilter;
import com.jl.pricing.engine.mapper.Mapper;
import com.jl.pricing.engine.mapper.ProductMapper;
import com.jl.pricing.engine.vo.ProductList;
import com.jl.pricing.engine.vo.ProductVo;

/**
 * 
 * @author Manish Kaklij
 *
 */
@Service
public class ProductPriceReductionService {
	
	@Autowired
	private ProductFilter productFilter;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductLookupService productLookupService;
	
	public List<Product> getReducedPriceProducts(String categoryId, String labelType){
		return convertVotoBean(getReducedPriceSortedProducts(categoryId), labelType);
	}
	
	private List<ProductVo> getReducedPriceSortedProducts(String categoryId){
		List<ProductVo> prodList =  productFilter.filter(productLookupService.productLookup(categoryId));
		prodList.sort((p1,p2) -> p2.getPrice().getPriceReduction().compareTo(p1.getPrice().getPriceReduction()));
		return prodList;
	}
	
	private List<Product> convertVotoBean(List<com.jl.pricing.engine.vo.ProductVo> productVos, String labelType) {
		List<Product> products = new ArrayList<Product>();
		for(com.jl.pricing.engine.vo.ProductVo productVo : productVos) {
			Product product = new Product();
			Map<String, Object> map = new HashMap<String, Object>() {
				{
					put(Mapper.DESTINATION_OBJECT, product);
					put(Mapper.SOURCE_OBJECT, productVo);
					put(Mapper.LABEL_TYPE, labelType);
				}
			};
			productMapper.map(map);
			products.add(product);
		}
		return products;
	}

}
