package com.januszhou.btcm;

import android.app.Application;
import android.util.Log;
import com.januszhou.btcm.HttpRequest;

public class BtcmApplication extends Application{
	
	private static final String TAG = BtcmApplication.class.getSimpleName();
	private static final String url = "https://market.huobi.com/staticmarket/td.html";
	private String btcprice_huobi;
	private boolean serviceRunning;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		this.btcprice_huobi = "0";
	}
	
	public String getBtcPrice_huobi(){
		String price=null;
		try{
			String s=HttpRequest.sendGet(url, "");
	        String tags=",";
	        String [] re = s.split(tags);
	        price = re[re.length-3]; 
		}catch (Exception e){
			Log.e(TAG, e.toString());
			e.printStackTrace();
		}
		return price;
	}

	public String getBtcprice_huobi() {
		return btcprice_huobi;
	}

	public void setBtcprice_huobi(String btcprice_huobi) {
		this.btcprice_huobi = btcprice_huobi;
	}

	public void setServiceRunning(boolean serviceRunning) {
		// TODO Auto-generated method stub
		this.serviceRunning = serviceRunning;
	}
	
	public boolean isServiceRunning() {
		
		return serviceRunning;
	}

}
