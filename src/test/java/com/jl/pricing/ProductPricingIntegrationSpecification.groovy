package com.jl.pricing

import org.apache.commons.lang.StringUtils
import org.junit.Test
import org.springframework.boot.test.context.TestComponent

import groovyx.net.http.RESTClient
import spock.lang.Specification
import spock.lang.Unroll

/**
 *
 * @author Manish Kaklij
 *
 */
class ProductPricingIntegrationSpecification extends Specification {
	
	private static final String REST_URL = "http://localhost:8080/category/%s/reducedprice/products"
	
	def getRestClient(String categoryId, String lableType) {
		return new RESTClient(String.format(REST_URL, categoryId))
	}
	
	def getResponse(categoryId, lableType) {
		RESTClient restClient = getRestClient(categoryId, lableType)
		return restClient.get(
			headers:[
				"Accept": "application/json"
			],
			query:[
				'labelType': lableType
			]
		)
		
	}
	
	@Test
	@Unroll
	def "should return more than zero records #expectedDataSize for category #categoryId"(){
		when:
		def response = getResponse(categoryId, "ShowWasNow")
		
		then:
		assert response.status == 200
		def result = response.getData()
		assert (result.size() > 0 == expectedDataSize)
		
		where:
		categoryId 	| expectedDataSize
		"600001506"	| true
		"1234567"	| false
	}
	
	@Test
	@Unroll
	def "should contain #priceLable & must not contain #noPriceLable if labelType is #labelType"(){
		when:
		def response = getResponse("600001506", labelType)
		
		then:
		assert response.status == 200
		def result = response.getData()
		assert result[0].priceLabel.contains(priceLable)
		assert !result[0].priceLabel.contains(noPriceLable)
		
		where:
		labelType 			| priceLable	| noPriceLable
		null				| "Was"			| "then"
		""					| "Was"			| "then"
		"asdassa"			| "Was"			| "then"
		"ShowWasNow"		| "Was"			| "then"
		"ShowWasThenNow"	| "then"		| "% off"
		"ShowPercDscount"	| "% off"		| "Was"
	}
}
