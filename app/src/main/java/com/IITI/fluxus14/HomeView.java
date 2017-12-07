package com.IITI.fluxus14;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.view.View;

public class HomeView extends View{

	Bitmap flux;
	Paint texx;
	Typeface font;
	
	public HomeView(Context context) {
		super(context);
		flux = BitmapFactory.decodeResource(getResources(), R.drawable.f13m);
		font = Typeface.createFromAsset(context.getAssets(), "Gotham Nights.ttf");
		texx = new Paint();
		texx.setTypeface(font);
		texx.setTextAlign(Align.CENTER);
		texx.setTextSize(80);
		texx.setARGB(255, 19, 106, 255);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawBitmap(flux, (canvas.getWidth()-flux.getWidth())/2, canvas.getHeight()/3, null);
		canvas.drawText("Enter", canvas.getWidth()/2, canvas.getHeight()/3+flux.getHeight(), texx);
	}

}
