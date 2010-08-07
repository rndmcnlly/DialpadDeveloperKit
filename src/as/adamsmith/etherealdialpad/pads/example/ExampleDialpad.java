package as.adamsmith.etherealdialpad.pads.example;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;

public class ExampleDialpad extends Dialpad {	
	ExampleView exampleView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		exampleView = new ExampleView(this);
		setContentView(exampleView);
	}
	
	class ExampleView extends View {
		final int black = 0xFF000000;
		final int white = 0xFFFFFFFF;

		public ExampleView(Context context) {
			super(context);
			setBackgroundColor(black);
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			final float u = Math.min(1, Math.max(0,event.getX() / getWidth()));
			final float v = Math.min(1, Math.max(0,1.0f - event.getY() / getHeight()));
			
			
			if(event.getAction() == MotionEvent.ACTION_UP) {

				try {
					synthService.primaryOff();
					synthService.primaryXY(u, v);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
				setBackgroundColor(black);
				invalidate();
				
			} else {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					try {
						synthService.primaryOn();
						synthService.primaryXY(u, v);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else if(event.getAction() == MotionEvent.ACTION_MOVE) {
					
					try {
						synthService.primaryXY(u, v);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

				setBackgroundColor(white);
				invalidate();
			}
			
			return true;
		}
		
	}
}
