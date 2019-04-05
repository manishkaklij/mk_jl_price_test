package com.jl.pricing.engine.mapper

import org.junit.Test

import com.jl.pricing.engine.bean.ColorSwatch
import com.jl.pricing.engine.vo.ColorSwatchVo

import spock.lang.Specification
import spock.lang.Unroll

/**
 *
 * @author Manish Kaklij
 *
 */
class ColorSwatchMapperSpecification extends Specification {
	
	ColorSwatchMapper testObj
	ColorSwatchVo colorSwatchVoMock = Mock()
	ColorSwatch colorSwatchBean = Spy()
	Map<String, Object> map = ["destinationObject": colorSwatchBean, "sourceObject": colorSwatchVoMock]
	
	def setup() {
		testObj = Spy()
	}
	
	@Test
	@Unroll
	def "should return rgbColor #rgbColor for basicColor #basicColor" () {
		given:
			colorSwatchVoMock.getBasicColor() >> basicColor
			testObj.copyVoToBean(colorSwatchBean, colorSwatchVoMock) >> {}
		when:
			testObj.map(map)
		then:
			assert colorSwatchBean.getRgbColor().equals(rgbColor)
		where:
			basicColor	| rgbColor
			"Red"		| "#FF0000"
			"Green"		| "#00FF00"
			"Blue"		| "#0000FF"
			"Yellow"	| "#FFFF00"
			"Purple"	| "#800080"
			"White"		| ""
			""			| ""
			null		| ""
	}
	
}
