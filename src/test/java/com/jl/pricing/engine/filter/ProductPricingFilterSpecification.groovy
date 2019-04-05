package com.jl.pricing.engine.filter

import org.junit.Test

import com.jl.pricing.engine.vo.PriceVo
import com.jl.pricing.engine.vo.ProductVo

import spock.lang.Specification
import spock.lang.Unroll

/**
 *
 * @author Manish Kaklij
 *
 */
public class ProductPricingFilterSpecification extends Specification {
	
	ProductPricingFilter testObj;
	ProductVo productVoMock1 = Mock()
	ProductVo productVoMock2 = Mock()
	ProductVo productVoMock3 = Mock()
	List<ProductVo> productVos = [productVoMock1, productVoMock2, productVoMock3]
	
	PriceVo priceVoMock1 = Mock()
	PriceVo priceVoMock2 = Mock()
	PriceVo priceVoMock3 = Mock()
	
	def setup() {
		testObj = Spy()
		productVoMock1.getPrice() >> priceVoMock1
		productVoMock2.getPrice() >> priceVoMock2
		productVoMock3.getPrice() >> priceVoMock3
	}
	
	@Test
	@Unroll
	def void "filter the list and return expectedSize #expectedSize when priceReduction1 #priceReduction1 , priceReduction2 #priceReduction2 & priceReduction3 #priceReduction3"() {
		given:
			priceVoMock1.getPriceReduction() >> priceReduction1
			priceVoMock2.getPriceReduction() >> priceReduction2
			priceVoMock3.getPriceReduction() >> priceReduction3
		when:
			def result = testObj.filter(productVos)
		then:
			result.size() == expectedSize
		where:
			priceReduction1 | priceReduction2 	| priceReduction3 	| expectedSize
			10 				| 15 				| 20 				| 3
			0 				| 15				| 20 				| 2
			10 				| 0 				| 20 				| 2
			10 				| 15 				| 0 				| 2
			10 				| 0 				| 0 				| 1
			0 				| 0 				| 0 				| 0
	}
}
