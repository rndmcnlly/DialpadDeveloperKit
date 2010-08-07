package as.adamsmith.etherealdialpad.pads.example;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Window;
import android.view.WindowManager;
import as.adamsmith.etherealdialpad.dsp.ISynthService;

public abstract class Dialpad extends Activity {
	ISynthService synthService = null;
	SynthServiceConnection synthServiceConnection = new SynthServiceConnection();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		bindService(
				new Intent(ISynthService.class.getName()),
				synthServiceConnection,
				Context.BIND_AUTO_CREATE);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		unbindService(synthServiceConnection);
	}
	
	class SynthServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			synthService = ISynthService.Stub.asInterface(service);
			onSynthServiceConnected();
			}

		@Override
		public void onServiceDisconnected(ComponentName className) {
			synthService = null;
			onSynthServiceDisconnected();
		}
	}
	
	protected void onSynthServiceConnected() {}
	protected void onSynthServiceDisconnected() {}
}
