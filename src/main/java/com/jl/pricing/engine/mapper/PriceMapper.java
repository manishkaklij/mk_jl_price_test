package com.jl.pricing.engine.mapper;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jl.pricing.engine.bean.Product;
import com.jl.pricing.engine.vo.PriceVo;

/**
 * 
 * @author Manish Kaklij
 *
 */
@Service
public class PriceMapper implements Mapper {
	
	private static enum LABEL_TYPES {ShowWasNow, ShowWasThenNow, ShowPercDscount};
	
	public static final Map<String, String> PRICE_LABEL_FORMAT = new HashMap<String, String>() {
		{
			put(LABEL_TYPES.ShowWasNow.toString(), "Was %1$s, now %2$s");
			put(LABEL_TYPES.ShowWasThenNow.toString(), "Was %1$s,%3$s now %2$s");
			put(LABEL_TYPES.ShowPercDscount.toString(), "%4$s off - now %2$s");
		}
	};
	public static final String THEN_FORMAT = " then %s,";
	
	@Override
	public void map(Object destObj, Object sourceObj, Map<String, Object> map) {
		Object label = map.get(LABEL_TYPE);
		String labelType = LABEL_TYPES.ShowWasNow.toString();
		if(!StringUtils.isEmpty(label) && Arrays.stream(LABEL_TYPES.values()).anyMatch((t) -> t.name().equals((String) label))) {
			labelType = (String) label;
		}
		mapPrice((Product) destObj, (PriceVo) sourceObj, labelType);
	}
	
	private void mapPrice(Product productBean, PriceVo priceVo, String labelType) {
		Currency currency = Currency.getInstance(priceVo.getCurrency());
		String currencySymbol = currency.getSymbol();
		productBean.setNowPrice(formatNowPrice(priceVo, currencySymbol));
		productBean.setPriceLabel(formatPriceLabel(priceVo, currencySymbol, labelType));
	}

	private String formatNowPrice(PriceVo priceVo, String currencySymbol) {		
		return formatPrice(priceVo.getNow(), currencySymbol);
	}
	
	private String formatPriceLabel(PriceVo priceVo, String currencySymbol, String labelType) {
		String thenPrice = "";
		if(getDouble(priceVo.getThen2()) > 0) {
			thenPrice = String.format(THEN_FORMAT, formatPrice(priceVo.getThen2(), currencySymbol));
		} else if(getDouble(priceVo.getThen1()) > 0) {
			thenPrice = String.format(THEN_FORMAT, formatPrice(priceVo.getThen1(), currencySymbol));
		}
		Double wasPrice = getDouble(priceVo.getWas());
		Double percentValue = (wasPrice - priceVo.getNow())*100/wasPrice;
		String percentOff = formatPrice(percentValue) +"%";
		
		return String.format(PRICE_LABEL_FORMAT.get(labelType), formatPrice(wasPrice, currencySymbol), formatPrice(priceVo.getNow(), currencySymbol), thenPrice, percentOff);
	}
	
	private String formatPrice(Double price) {
		String priceValue = "";
		if(price < 10) {			
			priceValue= String.format("%.2f", price);  
		} else {			
			DecimalFormat df = new DecimalFormat("#.##");
			priceValue = df.format(price);
		}
		return priceValue;
	}
	
	private String formatPrice(Double price, String currencySymbol) {
		return currencySymbol + formatPrice(price);
	}
	
	private String formatPrice(String price, String currencySymbol) {
		Double priceValue = getDouble(price);
		return currencySymbol + formatPrice(priceValue);
	}
	
	private Double getDouble(Object value) {
		try {
			return Double.parseDouble(value.toString());
		} catch (Exception e) {
			return 0d;
		}
	}

}
