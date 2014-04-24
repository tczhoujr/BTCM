package com.januszhou.btcm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdateService extends Service{
	
	public static final int DELAY = 10000;
	public static final String TAG = "UpdateService";
	public static final String NEW_PRICE_EXTRA = "new_price_extra";
	private boolean runFlag;
	private Updater updater;
	private BtcmApplication btcm;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		updater = new Updater();
		btcm = (BtcmApplication) this.getApplication();
		
	}
	
	
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		this.runFlag=true;
		this.updater.start();
		this.btcm.setServiceRunning(true);
		
		Log.d(TAG, "onStart");
		return super.onStartCommand(intent, flags, startId);
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.runFlag = false;
		this.updater.interrupt();
		this.updater = null;
		this.btcm.setServiceRunning(false);
		
		Log.d(TAG, "onDestroy");
	}


	public class Updater extends Thread{
		
		Intent intent;
		
		public Updater(){
			super("UpdaterService-Updater");
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			UpdateService updateService = UpdateService.this;
			BtcmApplication bApplication = (BtcmApplication) updateService.getApplication();
			while(updateService.runFlag){
				Log.d(TAG, "Updater running");
				try{
					//some work goes here...					
					bApplication.updateMarketData();
					intent = new Intent("com.januszhou.btcm.MSG_NEW_PRICE");
					//intent.putExtra(NEW_PRICE_EXTRA, price);
					intent.putExtra(NEW_PRICE_EXTRA,"");
					updateService.sendBroadcast(intent);
					Thread.sleep(DELAY);
				}catch(InterruptedException e){
					updateService.runFlag = false;
				}
			}
		}

		
	}

}
