package com.jl.pricing.engine.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.jl.pricing.engine.bean.ColorSwatch;
import com.jl.pricing.engine.vo.ColorSwatchVo;

/**
 * 
 * @author Manish Kaklij
 *
 */
@Service
public class ColorSwatchMapper implements Mapper{
	
	private static final Map<String, String> rgbColorCodes = new HashMap<String, String>() {
		{
			put("Red", "#FF0000");
			put("Green", "#00FF00");
			put("Blue", "#0000FF");
			put("Yellow", "#FFFF00");
			put("Purple", "#800080");
		}
	};
	
	@Override
	public void map(Object destObj, Object sourceObj, Map<String, Object> map) {
		mapColorSwatch((ColorSwatch) destObj, (ColorSwatchVo) sourceObj);
	}
	
	private void mapColorSwatch(ColorSwatch colorSwatchBean, ColorSwatchVo colorSwatchVo) {
		try {
			copyVoToBean(colorSwatchBean, colorSwatchVo);
			String rgbColor = rgbColorCodes.get(colorSwatchVo.getBasicColor());
			colorSwatchBean.setRgbColor(StringUtils.isEmpty(rgbColor)? "" : rgbColor);
		} catch (IllegalAccessException | InvocationTargetException e) {
		}
	}

	protected void copyVoToBean(ColorSwatch colorSwatchBean, ColorSwatchVo colorSwatchVo)
			throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(colorSwatchBean, colorSwatchVo);
	}
}
