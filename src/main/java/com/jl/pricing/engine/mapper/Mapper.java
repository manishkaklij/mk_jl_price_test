package com.jl.pricing.engine.mapper;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Manish Kaklij
 *
 */
@Service
public interface Mapper {
	
	public static final String DESTINATION_OBJECT = "destinationObject";
	public static final String SOURCE_OBJECT = "sourceObject";
	public static final String LABEL_TYPE = "labelType";
	
	public default void map(Map<String, Object> map) {
		map(map.get(DESTINATION_OBJECT), map.get(SOURCE_OBJECT), map);
	}
	
	public void map(Object destObj, Object sourceObj, Map<String, Object> map);

}
