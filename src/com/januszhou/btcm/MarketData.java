package com.januszhou.btcm;

public class MarketData {
	public String name;
	public String unit;
	public String lastestPrice;
	//private String buy1;
	//private String sell1;
	public String maxPrice;
	public String minPrice;
	
	public MarketData(String name, String unit, String lastestPrice, String maxPrice,
			String minPrice) {
		super();
		this.name = name;
		this.unit = unit;
		this.lastestPrice = lastestPrice;
		this.maxPrice = maxPrice;
		this.minPrice = minPrice;
	}
}
