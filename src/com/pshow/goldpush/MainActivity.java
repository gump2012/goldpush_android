package com.pshow.goldpush;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.igexin.sdk.PushManager;

import android.util.Log; 
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

public class MainActivity extends Activity {
	String strcon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PlaceholderFragment bFrag  = new PlaceholderFragment(); 
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, bFrag).commit();
		}
		PushManager.getInstance().initialize(this.getApplicationContext());
		strcon = PushManager.getInstance().getClientid(this);
		Log.i("0",strcon);
		Myapp.getInstance().name = strcon;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		public EditText mytext;
		
		public PlaceholderFragment() {
		}
		@Override
		
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			Button registBtn = (Button)rootView.findViewById(R.id.button1);
			mytext = (EditText)rootView.findViewById(R.id.editText1);
			if(registBtn == null){
				Log.i("0","null");
			}
			else{
				Log.i("0","not null");
				
				registBtn.setOnClickListener(new View.OnClickListener() {  
				      
				    @Override  
				    public void onClick(View v) {  
				        // TODO Auto-generated method stub  
				        Log.i("0", "button onClick");  
				        if(mytext != null){
				        	if(mytext.length() == 11){
				        		
				        		if(Myapp.getInstance().name.length() > 0){
				        			String strUrl = Myapp.getInstance().domain;
					        		strUrl += "Users/userRegistration";
					        		strUrl += "?userid=";
					        		strUrl += Myapp.getInstance().name;
					        		strUrl += "&phoneNum=";
					        		strUrl += mytext.getText();
					        		strUrl += "&deviceType=android";
					        		
					        		URL url = null;
					        		try{
					        			String result="";
					                    
					                    HttpGet httpGet=new HttpGet(strUrl);
					                    //ȡ��HTTP response
					                    HttpResponse response=new DefaultHttpClient().execute(httpGet);
					                    //��״̬��Ϊ200
					                    if(response.getStatusLine().getStatusCode()==200){
					                            //ȡ��Ӧ���ַ���
					                            HttpEntity entity=response.getEntity();
					                            result=EntityUtils.toString(entity, HTTP.UTF_8);
					                    }
					        			
//					        			new AlertDialog.Builder(getActivity()) 
//						                .setMessage(result)  
//						                .setPositiveButton("ȷ��",  
//						                        new DialogInterface.OnClickListener() {  
//						                            @Override  
//						                            public void onClick(DialogInterface dialog,  
//						                                    int which) {  
//						                                // TODO Auto-generated method stub  
//						  
//						                            }  
//						                        }).setNegativeButton("ȡ��", null).create()  
//						                .show();
					        		}catch(Exception e){
					        			e.printStackTrace();
					        		}
				        		}
				        		else{
					        		new AlertDialog.Builder(getActivity()) 
					                .setMessage("û���豸id")  
					                .setPositiveButton("ȷ��",  
					                        new DialogInterface.OnClickListener() {  
					                            @Override  
					                            public void onClick(DialogInterface dialog,  
					                                    int which) {  
					                                // TODO Auto-generated method stub  
					  
					                            }  
					                        }).setNegativeButton("ȡ��", null).create()  
					                .show();
				        		}
				        		
				        	}else{
				        		new AlertDialog.Builder(getActivity()) 
				                .setTitle("�绰���볤�Ȳ���")  
				                .setPositiveButton("ȷ��",  
				                        new DialogInterface.OnClickListener() {  
				                            @Override  
				                            public void onClick(DialogInterface dialog,  
				                                    int which) {  
				                                // TODO Auto-generated method stub  
				  
				                            }  
				                        }).setNegativeButton("ȡ��", null).create()  
				                .show();
				        	}
				        }
				         
				    }  
				});  
			}
			return rootView;
		}
	}

}
