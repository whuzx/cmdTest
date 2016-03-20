package com.androdi.asus.commandtest;

import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class CommandService extends Service {

	
	private static final String TAG = "CommandService";
	private static boolean Enable_LOG = true;
	
	private ICommandService.Stub mBinder = new ICommandService.Stub() {   
		  
        @Override
		public  int doCommand(String command, List<String> result)
        {
    	    if(Enable_LOG) Log.d(TAG, "Command: " +  command);
    	    
    	  // String[] cc = command.split(" ");
           if(command.startsWith("cmdtest"))
    	    {
    	    	doFdiskCommand(command, result);
    	    	return -1;
    	    }
    	    
    		int nRes = -1;
    		
    		if (result != null) {
    			result.clear();
			}
    		
    		try {
    			//
    			
    			File cmdString=Environment.getExternalStorageDirectory();
    			
    			String[] filesStrings=cmdString.list();
    			Log.i(TAG, "files="  + Arrays.deepToString(filesStrings));

    			String myprefixString=cmdString.getPath();
    			Log.i(TAG, "files=" + myprefixString);
    			Process process = Runtime.getRuntime().exec( command);

    			LineNumberReader input = new LineNumberReader( new InputStreamReader(
    					process.getInputStream()));
    			
    			String line;
    			
    			if (result != null) {
    				while ((line = input.readLine()) != null) {
        				if(Enable_LOG) Log.d(TAG, line);
        				result.add(new String(line));
        			}
    			}else{
    				if(Enable_LOG) Log.i(TAG, "line is null");
    			}
    			
    			input.close();
    			
    			nRes = process.waitFor();
    			
    		} catch (java.io.IOException e) {
    			if(Enable_LOG) Log.e(TAG, "", e);
    		} catch (InterruptedException e) {
    			if(Enable_LOG) Log.e(TAG, "", e);
    		}
    		
    		if(Enable_LOG) Log.d(TAG, "CommandResult: %d" +  nRes);
    		
    		return nRes;
        }
    }; 
    
	
    
//    private Handler mHandler = new Handler() {   
//    	  
//        @Override  
//        public void handleMessage(Message msg) {   
//            //callBack();   
//            super.handleMessage(msg);   
//        }   
//    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mBinder;
    }

    protected int doFdiskCommand(String command, List<String> result) {
	// TODO Auto-generated method stub
    	
    	List<String> Parmters=new ArrayList<String>();
		Parmters.add("m\n");
		Parmters.add("a\n");
		Parmters.add("2\n");
		Parmters.add("w\n");
    	
    	int nRes = -1;
		if (result != null) {
			result.clear();
		}
	      // InputStream is = null;
	       Process process = null;
	       DataOutputStream os = null;
	       LineNumberReader input = null ;
	       
	       try {
	           process = Runtime.getRuntime().exec(command);
	           os = new DataOutputStream(process.getOutputStream());
	          // is = process.getInputStream();
              input = new LineNumberReader( new InputStreamReader(
			  process.getInputStream()));
	        //   os.write((command + "\n").getBytes("UTF-8"));
              if(Parmters.size()>0)
              {
            	  for(int i=0;i<Parmters.size();i++)
            	  { 
            		  os.writeBytes(Parmters.get(i)); 
            	  }
              }
             // os.writeBytes("m" + "\n");
	          // os.writeBytes("q\n");
	           os.flush();
	           nRes=process.waitFor();
	       } catch (Exception e) {
	           return nRes;
	       } finally {
	           try {
	              if (os != null) { 
	            		if (result != null) {
	            			String line;
	            			while ((line = input.readLine()) != null) {
	                				if(Enable_LOG) Log.d(TAG, line);
	                				 result.add(new String(line));
	                			}
	            			
	        			}
	                  os.close();
	              }
	              process.destroy();
	           } catch (Exception e) {
	        	   
	           }
	       }

		if(Enable_LOG) Log.d(TAG, "fdisk: %d" +  nRes);
		return nRes;
    }
    
    	
//    private void activeBootDevice(){
//    	
//		List<String> Parmters=null;
//		
//		Parmters.add("a\n");
//		Parmters.add("2\n");
//		Parmters.add("w\n");
//		
//		if(NativeCommand.doMoreParamterCommand(NativeCommand.COMMAND_SUDO+" "+NativeCommand.COMMAND_FDISK+ " " + 
//				activePartition,Parmters) == 0)
//			EasyInstall.isActiveHDSucessBoolean=true;
//		else
//			EasyInstall.isActiveHDSucessBoolean=false;
//		
//		
//		if(ENABLE_LOG) Log.d(TAG, "set active HD Res: " + EasyInstall.isActiveHDSucessBoolean);
//		
//			
//		
//	}


	@Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
    	Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
