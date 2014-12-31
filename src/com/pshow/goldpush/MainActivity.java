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
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.conn.params.ConnManagerParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
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
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
	    }
		
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
					        		
					        		HttpClient httpClient = new DefaultHttpClient(); // �½�HttpClient����  
					                HttpConnectionParams  
					                        .setConnectionTimeout(httpClient.getParams(), 10000); // �������ӳ�ʱ  
					                HttpConnectionParams.setSoTimeout(httpClient.getParams(), 10000); // �������ݶ�ȡʱ�䳬ʱ  
					                ConnManagerParams.setTimeout(httpClient.getParams(), 10000); // ���ô����ӳ���ȡ���ӳ�ʱ  
					          
					                HttpGet httpget = new HttpGet(strUrl); // ��ȡ����  
					          
					                try {  
					                    HttpResponse response = httpClient.execute(httpget); // ִ�����󣬻�ȡ��Ӧ���  
					                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // ��Ӧͨ��  
					                        String result = EntityUtils.toString(response.getEntity(),  
					                                "UTF-8");  
					                        if(result.equals("success")){
					                        	new AlertDialog.Builder(getActivity()) 
								                .setMessage("ע��ɹ�")  
								                .setPositiveButton("ȷ��",  
								                        new DialogInterface.OnClickListener() {  
								                            @Override  
								                            public void onClick(DialogInterface dialog,  
								                                    int which) {  
								                                // TODO Auto-generated method stub  
								  
								                            }  
								                        }).setNegativeButton("ȡ��", null).create()  
								                .show();
					                        }else{
					                        	new AlertDialog.Builder(getActivity()) 
								                .setMessage("ע��ʧ��")  
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
					                    } else {  
					                    	new AlertDialog.Builder(getActivity()) 
							                .setMessage("ע��ʧ��")  
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
					                } catch (ClientProtocolException e) {  
					                    // TODO Auto-generated catch block  
					                    e.printStackTrace();  
					                } catch (IOException e) {  
					                    // TODO: handle exception  
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
