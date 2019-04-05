package com.jl.pricing.engine.mapper

import org.junit.Test

import com.jl.pricing.engine.bean.Product
import com.jl.pricing.engine.vo.PriceVo

import spock.lang.Specification
import spock.lang.Unroll

/**
 *
 * @author Manish Kaklij
 *
 */
class PriceMapperSpecification extends Specification {
	
	PriceMapper testObj
	PriceVo priceVoMock = Mock()
	Product productBean = Spy()
	Map<String, Object> map = ["destinationObject": productBean, "sourceObject": priceVoMock]
	
	def setup() {
		testObj = Spy()
		priceVoMock.getCurrency() >> "GBP"
		priceVoMock.getWas() >> "80"
	}
	
	@Test
	@Unroll
	def "should return expectedNowPrice #expectedNowPrice for nowPrice #nowPrice"() {
		given:
		map.put("labelType", "ShowWasNow")
		priceVoMock.getNow() >> nowPrice
		
		when:
		testObj.map(map)
		
		then:
		assert productBean.getNowPrice().equals(expectedNowPrice)
		
		where:
		nowPrice 			| expectedNowPrice
		2					| "£2.00"
		2.00				| "£2.00"
		1.75				| "£1.75"
		10					| "£10"
		10.00				| "£10"
		10.25				| "£10.25"
	}
	
	@Test
	@Unroll
	def "should contain #priceLable & must not contain #noPriceLable if labelType is #labelType"() {
		given:
		map.put("labelType", labelType)
		priceVoMock.getNow() >> 35
		priceVoMock.getThen2() >> "45"
		
		when:
		testObj.map(map)
		
		then:
		assert productBean.getPriceLabel().contains(priceLable)
		assert !productBean.getPriceLabel().contains(noPriceLable)
		
		where:
		labelType 			| priceLable	| noPriceLable
		null				| "Was"			| "then"
		""					| "Was"			| "then"
		"asdassa"			| "Was"			| "then"
		"ShowWasNow"		| "Was"			| "then"
		"ShowWasThenNow"	| "then"		| "% off"
		"ShowPercDscount"	| "% off"		| "Was"
		
	}
	
	@Test
	@Unroll
	def "should return #priceLableContent based on then1 65 and then2 is #then2"() {
		given:
		map.put("labelType", "ShowWasThenNow")
		priceVoMock.getNow() >> 35
		priceVoMock.getThen1() >> "65"
		priceVoMock.getThen2() >> then2
		
		when:
		testObj.map(map)
		
		then:
		assert productBean.getPriceLabel().contains("then")
		assert productBean.getPriceLabel().contains(priceLableContent)
		assert !productBean.getPriceLabel().contains(noPriceLableContent)
		assert !productBean.getPriceLabel().contains("% off")
		
		where:
		then2	| priceLableContent	| noPriceLableContent
		null	| "65"				| "45"
		"45"	| "45"				| "65"
	}
	
	@Test
	@Unroll
	def "should not contain then part in the label if then2 & then1 are both null or 0"() {
		given:
		map.put("labelType", "ShowWasThenNow")
		priceVoMock.getNow() >> 35
		priceVoMock.getThen1() >> then1
		priceVoMock.getThen2() >> then2
		
		when:
		testObj.map(map)
		
		then:
		assert !productBean.getPriceLabel().contains("then")
		
		where:
		then1	| then2
		""		| ""
		""		| null
		null	| ""
		null	| null
		
	}
}
