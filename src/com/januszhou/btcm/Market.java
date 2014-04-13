package com.januszhou.btcm;

public class Market{
	private String lastestPrice;
	//private String buy1;
	//private String sell1;
	private String maxPrice;
	private String minPrice;
	public String getLastestPrice() {
		return lastestPrice;
	}
	public void setLastestPrice(String lastestPrice) {
		this.lastestPrice = lastestPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
}