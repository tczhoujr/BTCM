package com.januszhou.btcm;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import com.januszhou.btcm.HttpRequest;

public class MarketData {
	
	public static final String TAG = "MarketData";
	
	public huobiMarket huobi;
	public okcoinMarket okcoin;
	public bitstampMarket bitstamp;
	
	public MarketData(){
		huobi = new huobiMarket();
		okcoin = new okcoinMarket();
		bitstamp = new bitstampMarket();
	}
	
	public void updateMarketData(){
		huobi.getMarketData();
		okcoin.getMarketData();
		bitstamp.getMarketData();
	}
	
	public huobiMarket getHuobiMarket(){
		return huobi;
	}
	
	public okcoinMarket getOkcoinMarket(){
		return okcoin;
	}
	
	public bitstampMarket getBitstampMarket(){
		return bitstamp;
	}

	public class huobiMarket extends Market{
		private static final String HUOBI_API_URL = "http://market.huobi.com/staticmarket/kline";
		//private static final String MINUTE = "001"+".html";
		//private static final String FIVE_MINUTE = "005"+".html";
		//private static final String FIFTEEN_MINUTE = "015"+".html";
		//private static final String THIRTY_MINUTE = "030"+".html";
		//private static final String HUOR = "060"+".html";
		private static final String DAY = "100"+".html";
		
		public void getMarketData(){
			try{
				String s=HttpRequest.sendGet(HUOBI_API_URL + DAY, "");
				s = s.substring(s.length()-100);
		        String [] re = s.split(",");
		        setLastestPrice(re[re.length-3]); 
		        setMinPrice(re[re.length-4]);
		        setMaxPrice(re[re.length-5]);
			}catch (Exception e){
				Log.e(TAG, e.toString());
				e.printStackTrace();
			}
		} 
	}
	
	public class okcoinMarket extends Market{
		private static final String OKCOIN_API_URL = "https://www.okcoin.com/api/ticker.do";

		public void getMarketData(){
			try{
				
				String s=HttpRequest.sendGet(OKCOIN_API_URL, "");
				JSONObject json;
			    try {
			    	json = new JSONObject(s);
			        String result = json.getString("ticker");
			        json = new JSONObject(result);
			        setLastestPrice(json.getString("last"));
			        setMaxPrice(json.getString("high"));
			        setMinPrice(json.getString("low"));
			        
			    } catch (JSONException e) {
			    	Log.e(TAG, e.toString());
			    	e.printStackTrace();
			    }
			    
			}catch (Exception e){
				Log.e(TAG, e.toString());
				e.printStackTrace();
			}
		} 
	}
	
	public class bitstampMarket extends Market{
		private static final String BITSTAMP_API_URL = "http://www.bitstamp.net/api/ticker/";
		
		public void getMarketData(){
			try{
				
				String s=HttpRequest.sendGet(BITSTAMP_API_URL, "");
				JSONObject json;
			    try {
			    	json = new JSONObject(s);
			        setLastestPrice(json.getString("last"));
			        setMaxPrice(json.getString("high"));
			        setMinPrice(json.getString("low"));
			        
			    } catch (JSONException e) {
			    	Log.e(TAG, e.toString());
			    	e.printStackTrace();
			    }
			    
			}catch (Exception e){
				Log.e(TAG, e.toString());
				e.printStackTrace();
			}
		} 
	}
	
	

}
