package com.januszhou.btcm;

import android.app.Application;

import com.januszhou.btcm.MarketData;

public class BtcmApplication extends Application{
	
	private static final String TAG = BtcmApplication.class.getSimpleName();
	private static final int MARKET_NUM = 3;
	private boolean serviceRunning;
	private Market market;
	

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		market = new Market();
		//marketDataArray = new MarketData[MARKET_NUM];
	}
	
	public MarketData getMarketData(int i){
		if (i >= 0 && i < MARKET_NUM)
			return market.marketDataArray[i];
		else
			return null;
	}
	
	public void updateMarketData(){
		market.updateMarketData();
	}
	
	public void setServiceRunning(boolean serviceRunning) {
		// TODO Auto-generated method stub
		this.serviceRunning = serviceRunning;
	}
	
	public boolean isServiceRunning() {
		
		return serviceRunning;
	}
	

}
