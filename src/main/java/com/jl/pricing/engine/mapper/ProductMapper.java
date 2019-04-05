package com.jl.pricing.engine.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jl.pricing.engine.bean.ColorSwatch;
import com.jl.pricing.engine.bean.Product;
import com.jl.pricing.engine.vo.ColorSwatchVo;
import com.jl.pricing.engine.vo.ProductVo;

/**
 * 
 * @author Manish Kaklij
 *
 */
@Service
public class ProductMapper implements Mapper{
	
	@Autowired
	private ColorSwatchMapper colorSwatchMapper;
	
	@Autowired
	private PriceMapper priceMapper;
	
	@Override
	public void map(Object destObj, Object sourceObj, Map<String, Object> map) {
		mapProduct((Product) destObj, (ProductVo) sourceObj, map);
	}
	
	private void mapProduct(Product productBean, ProductVo productVo, Map<String, Object> map) {
		try {
			BeanUtils.copyProperties(productBean, productVo);
			Map<String, Object> priceMapperMap = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			priceMapperMap.put(Mapper.SOURCE_OBJECT, productVo.getPrice());
			priceMapper.map(priceMapperMap);
			List<ColorSwatch> colorSwatchBeans = new ArrayList<ColorSwatch>();
			for(ColorSwatchVo colorSwatchVo: productVo.getColorSwatches()) {
				ColorSwatch colorSwatchBean = new ColorSwatch();
				Map<String, Object> colorSwatcherMapperMap = new HashMap<String, Object>(){
					{						
						put(Mapper.DESTINATION_OBJECT, colorSwatchBean);
						put(Mapper.SOURCE_OBJECT, colorSwatchVo);
					}
				};
				colorSwatchMapper.map(colorSwatcherMapperMap);
				colorSwatchBeans.add(colorSwatchBean);
			}
			productBean.setColorSwatches(colorSwatchBeans);
		} catch (IllegalAccessException | InvocationTargetException e) {
		}
	}

}
