package com.jl.pricing.engine.vo;

import java.io.Serializable;

/**
 * 
 * @author Manish Kaklij
 *
 */
public class PriceVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String was;
	private String then1;
	private String then2;
	private Object now;
	private String currency;
	
	public String getWas() {
		return was;
	}
	public void setWas(String was) {
		this.was = was;
	}
	public String getThen1() {
		return then1;
	}
	public void setThen1(String then1) {
		this.then1 = then1;
	}
	public String getThen2() {
		return then2;
	}
	public void setThen2(String then2) {
		this.then2 = then2;
	}
	public Double getNow() {
		return getDouble(now);
	}
	public void setNow(Object now) {
		this.now = now;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public Double getPriceReduction() {
		if(getDouble(was) > 0 && getDouble(now) > 0) {
			return getDouble(was) - getDouble(now);
		} else {
			return 0d;
		}
	}
	
	private Double getDouble(Object value) {
		try {
			return Double.parseDouble(value.toString());
		} catch (Exception e) {
			return 0d;
		}
	}
	
}
