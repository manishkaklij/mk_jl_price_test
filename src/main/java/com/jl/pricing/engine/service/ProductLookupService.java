package com.jl.pricing.engine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.jl.pricing.engine.vo.ProductList;
import com.jl.pricing.engine.vo.ProductVo;

/**
 * 
 * @author Manish Kaklij
 *
 */
@Service
public class ProductLookupService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String REST_URL = "https://jl-nonprod-syst.apigee.net/v1/categories/%s/products?key=%s";
	private static final String KEY = "2ALHCAAs6ikGRBoy6eTHA58RaG097Fma";
	
	public List<ProductVo> productLookup(String categoryId) {
		try {
			ProductList productList = restTemplate.getForObject(String.format(REST_URL, categoryId, KEY), ProductList.class);
			if(productList != null && productList.getProducts() != null) {
				return productList.getProducts();
			}
		} catch (RestClientException e) {
		}
		return new ArrayList<ProductVo>();
	}

}
