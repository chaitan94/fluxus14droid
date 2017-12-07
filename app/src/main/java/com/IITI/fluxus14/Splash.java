package com.IITI.fluxus14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		Thread timer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					startActivity(new Intent("com.IITI.fluxus14.HOME"));
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
