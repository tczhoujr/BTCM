package com.januszhou.btcm;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity {
	
	public static final String TAG = "MainActivity";
	public static final String NEW_PRICE_EXTRA = "new_price_extra";
	private TextView huobiLastPrice;
	private TextView okcoinLastPrice;
	private TextView bitstampLastPrice;
	BtcmApplication bApplication;
	MsgReceiver msgReceiver;
	IntentFilter intentFilter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		huobiLastPrice = (TextView) findViewById(R.id.huobi_priceView);	
		okcoinLastPrice = (TextView) findViewById(R.id.okcoin_priceView);
		bitstampLastPrice = (TextView) findViewById(R.id.bitstamp_priceView);
		bApplication = (BtcmApplication) this.getApplication();
		
		msgReceiver = new MsgReceiver();  
        intentFilter = new IntentFilter();  
        intentFilter.addAction("com.januszhou.btcm.MSG_NEW_PRICE");
        
		startService(new Intent(this, UpdateService.class));
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//register service
		registerReceiver(msgReceiver, intentFilter);  
	}
	
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopService(new Intent(this, UpdateService.class));
		unregisterReceiver(msgReceiver);  
	}



	class MsgReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//textView.setText(intent.getStringExtra(NEW_PRICE_EXTRA));
			huobiLastPrice.setText(bApplication.getMarketData().getHuobiMarket().getLastestPrice());
			okcoinLastPrice.setText(bApplication.getMarketData().getOkcoinMarket().getLastestPrice());
			bitstampLastPrice.setText(bApplication.getMarketData().getBitstampMarket().getLastestPrice());
			Log.d("MsgReceiver", "onReceived");
		}
		
	}
	

}
