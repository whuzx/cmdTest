package com.androdi.asus.commandtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CommandTestActivity extends Activity {
    
	private static final String TAG = "CommandTestActivity";

    private ICommandService mService;
    private EditText mEditCmd;
    private Button   mBtnCmd;
    private TextView mTxView;
    
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent i = new Intent(this, CommandService.class);
        bindService(i, mConnection, Context.BIND_AUTO_CREATE);
        
        mEditCmd = (EditText)this.findViewById(R.id.EditTextCmd);
        mBtnCmd  = (Button)this.findViewById(R.id.BtnCmd);
        mTxView  = (TextView)this.findViewById(R.id.TextViewSended);
        
        mBtnCmd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mService!=null){
					
					List<String> resArray = new ArrayList<String>();
					String cmdString = mEditCmd.getText().toString();
					int nRes = -1;
					try {
						nRes = mService.doCommand(cmdString, resArray);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, "", e);
					}
					
					String strRes="Command:" + cmdString + "\n";
					strRes = strRes + String.format("Result %d\n", nRes);
					
					for(String itemString : resArray)
					{
						strRes = strRes + itemString + "\n";
					}
					mTxView.setText(strRes);
				}
					
			}
		});
    }
    
    @Override
    protected void onDestroy() {
    	Log.d(TAG, "onDestroy");
    	unbindService(mConnection);
        super.onDestroy();
    }
    
    /**Connection to CommandService */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            mService = ICommandService.Stub.asInterface(service);
        }
    };
    
    
}