package com.jl.pricing.engine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jl.pricing.engine.bean.Product;
import com.jl.pricing.engine.service.ProductPriceReductionService;

/**
 * 
 * @author Manish Kaklij
 *
 */
@RestController
public class ProductController {
	
	private static final String HELP_TEXT = "Use below paths where categoryId is the category which you are interested in. Currently only supported categoryId is 600001506."
			+ "/category/{categoryId}/reducedprice/products "
			+ "/category/{categoryId}/reducedprice/products?labelType={labelTypeValue} where labelTypeValue can be "
			+"ShowWasNow - This is default behaviour, "
			+"ShowWasThenNow, "
			+"ShowPercDscount";
	
	@Autowired
	private ProductPriceReductionService productPriceReductionService;
	
	@RequestMapping(value = "/*", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getWelcomePage() {
		return HELP_TEXT;
	}
	
	@RequestMapping(value = "/category/{categoryId}/reducedprice/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getReducedPriceProducts(@PathVariable("categoryId") String categoryId, @RequestParam(name = "labelType", required = false, defaultValue = "") String labelType ) {
		return productPriceReductionService.getReducedPriceProducts(categoryId, labelType);
	}

}
