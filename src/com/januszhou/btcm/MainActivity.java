package com.januszhou.btcm;

import com.januszhou.btcm.HttpRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
	
	public static final String TAG = "MainActivity";
	private static final String url = "https://market.huobi.com/staticmarket/td.html";
	private String price;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.price = "0";
		textView = (TextView) findViewById(R.id.priceView);
		new BtcPrice().execute();		
	}
	
	class BtcPrice extends AsyncTask<String, Integer, String> {
		
		//Called to initiate the background activity
		@Override
		protected String doInBackground(String... statuses) {
			try{
				String s=HttpRequest.sendGet(url, "");
		        String tags=",";
		        String [] re = s.split(tags);
		        price = re[re.length-3]; 
		        return price;
			}catch (Exception e){
				Log.e(TAG, e.toString());
				e.printStackTrace();
				return "Failed to GET";
			}
		}
		 
		// Called when there's a status to be updated
	    @Override
	    protected void onProgressUpdate(Integer... values) { // 
	    	
	    	super.onProgressUpdate(values);
	      // Not used in this case
	    }

	    // Called once the background activity has completed
	    @Override
	    protected void onPostExecute(String result) { // 

	    	Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
	    	textView.setText(price);
	    }

	}


}
