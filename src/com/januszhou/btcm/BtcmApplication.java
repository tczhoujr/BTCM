package com.januszhou.btcm;

import android.app.Application;
import com.januszhou.btcm.MarketData;

public class BtcmApplication extends Application{
	
	private static final String TAG = BtcmApplication.class.getSimpleName();
	private boolean serviceRunning;
	private MarketData marketData;
	

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		marketData = new MarketData();
	}
	
	public MarketData getMarketData(){
		return this.marketData;
	}
	
	public void updateMarketData(){
		marketData.updateMarketData();
	}
	
	public void setServiceRunning(boolean serviceRunning) {
		// TODO Auto-generated method stub
		this.serviceRunning = serviceRunning;
	}
	
	public boolean isServiceRunning() {
		
		return serviceRunning;
	}
	

}
