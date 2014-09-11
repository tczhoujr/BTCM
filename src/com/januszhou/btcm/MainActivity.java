package com.januszhou.btcm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity {
	
	public static final String TAG = "MainActivity";
	public static final String NEW_PRICE_EXTRA = "new_price_extra";
	BtcmApplication btcm;
	MsgReceiver msgReceiver;
	IntentFilter intentFilter;
	ListView listView;
	SimpleAdapter simpleAdapter;
	List<HashMap<String, String>> listContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.list_layout);

		btcm = (BtcmApplication) this.getApplication();
		
		listView = (ListView) findViewById(R.id.listView);
		listContent = new ArrayList<HashMap<String, String>>(); 
		listContent = this.getData();
		String[] FROM = new String[] { "name", "price" ,"unit", "high", "low"};
		int[] TO = new int[] {R.id.titleView , R.id.priceView ,R.id.priceUnitView, R.id.highPriceView, R.id.lowPriceView};
		simpleAdapter = new SimpleAdapter(this, listContent, R.layout.list_item, FROM, TO); 
		
		listView.setAdapter(simpleAdapter);
		
		msgReceiver = new MsgReceiver();  
        intentFilter = new IntentFilter();  
        intentFilter.addAction("com.januszhou.btcm.MSG_NEW_PRICE");
     
		Log.d("MainActivity", "onCreate");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
			case R.id.action_Prefs:
				startActivity(new Intent(this, PrefsActivity.class));
				break;
			case R.id.action_refresh:
				Toast.makeText(this, "Refreshing...", Toast.LENGTH_LONG).show();
				break;
				
		}
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 
		//start service
		startService(new Intent(this, UpdateService.class));
		
		//register service
		registerReceiver(msgReceiver, intentFilter);  
	}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();	
		
		//stop service
		stopService(new Intent(this, UpdateService.class));
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		unregisterReceiver(msgReceiver);  
	}


	private List<HashMap<String, String>> getData() {
		//ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();  
        listContent.clear();
		HashMap<String, String> map = null;  
        for (int i = 0; i < BtcmApplication.MARKET_NUM; i++) {  
            map = new HashMap<String, String>();  
            map.put("name", btcm.getMarketData(i).name);  
            map.put("price", btcm.getMarketData(i).lastestPrice);
            map.put("unit", btcm.getMarketData(i).unit);
            map.put("high", btcm.getMarketData(i).maxPrice);
            map.put("low", btcm.getMarketData(i).minPrice);
            listContent.add(map);  
        }  
        return listContent;  
	}

	class MsgReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			getData();	
			simpleAdapter.notifyDataSetChanged();
			Log.d("MsgReceiver", "onReceived");
		}
		
	}
	

}
