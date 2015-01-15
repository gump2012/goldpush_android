package www.pshow.goldpush;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.conn.params.ConnManagerParams;

import www.pshow.goldpush.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log; 
import android.widget.Button;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.EditText;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.InstrumentedActivity;

public class MainActivity extends InstrumentedActivity {
	String strcon;
	public static boolean isForeground = false;
	private EditText msgText;
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
		
		msgText = (EditText)findViewById(R.id.msg_rec);
		
		JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this); 
		//Log.i("0",strcon);
		//Myapp.getInstance().name = strcon;
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
				        		
				        		if(Myapp.getInstance().name == null){
				        			Myapp.getInstance().name = JPushInterface.getRegistrationID(getActivity().getApplicationContext());
				        			Log.i("0",Myapp.getInstance().name);
				        		}
				        		
				        		
				        		if(Myapp.getInstance().name != null && Myapp.getInstance().name.length() > 0){
				        			String strUrl = Myapp.getInstance().domain;
					        		strUrl += "Users/userRegistration";
					        		strUrl += "?userid=";
					        		strUrl += Myapp.getInstance().name;
					        		strUrl += "&phoneNum=";
					        		strUrl += mytext.getText();
					        		strUrl += "&deviceType=android";
					        		
					        		HttpClient httpClient = new DefaultHttpClient(); // 新建HttpClient对象  
					                HttpConnectionParams  
					                        .setConnectionTimeout(httpClient.getParams(), 10000); // 设置连接超时  
					                HttpConnectionParams.setSoTimeout(httpClient.getParams(), 10000); // 设置数据读取时间超时  
					                ConnManagerParams.setTimeout(httpClient.getParams(), 10000); // 设置从连接池中取连接超时  
					          
					                HttpGet httpget = new HttpGet(strUrl); // 获取请求  
					          
					                try {  
					                    HttpResponse response = httpClient.execute(httpget); // 执行请求，获取响应结果  
					                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 响应通过  
					                        String result = EntityUtils.toString(response.getEntity(),  
					                                "UTF-8");  
					                        if(result.equals("success")){
					                        	new AlertDialog.Builder(getActivity()) 
								                .setMessage("注册成功")  
								                .setPositiveButton("确定",  
								                        new DialogInterface.OnClickListener() {  
								                            @Override  
								                            public void onClick(DialogInterface dialog,  
								                                    int which) {  
								                                // TODO Auto-generated method stub  
								  
								                            }  
								                        }).setNegativeButton("取消", null).create()  
								                .show();
					                        }else{
					                        	new AlertDialog.Builder(getActivity()) 
								                .setMessage("注册失败")  
								                .setPositiveButton("确定",  
								                        new DialogInterface.OnClickListener() {  
								                            @Override  
								                            public void onClick(DialogInterface dialog,  
								                                    int which) {  
								                                // TODO Auto-generated method stub  
								  
								                            }  
								                        }).setNegativeButton("取消", null).create()  
								                .show();
					                        }
					                    } else {  
					                    	new AlertDialog.Builder(getActivity()) 
							                .setMessage("注册失败")  
							                .setPositiveButton("确定",  
							                        new DialogInterface.OnClickListener() {  
							                            @Override  
							                            public void onClick(DialogInterface dialog,  
							                                    int which) {  
							                                // TODO Auto-generated method stub  
							  
							                            }  
							                        }).setNegativeButton("取消", null).create()  
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
					                .setMessage("没有设备id")  
					                .setPositiveButton("确定",  
					                        new DialogInterface.OnClickListener() {  
					                            @Override  
					                            public void onClick(DialogInterface dialog,  
					                                    int which) {  
					                                // TODO Auto-generated method stub  
					  
					                            }  
					                        }).setNegativeButton("取消", null).create()  
					                .show();
				        		}
				        		
				        	}else{
				        		new AlertDialog.Builder(getActivity()) 
				                .setTitle("电话号码长度不符")  
				                .setPositiveButton("确定",  
				                        new DialogInterface.OnClickListener() {  
				                            @Override  
				                            public void onClick(DialogInterface dialog,  
				                                    int which) {  
				                                // TODO Auto-generated method stub  
				  
				                            }  
				                        }).setNegativeButton("取消", null).create()  
				                .show();
				        	}
				        }
				         
				    }  
				});  
			}
			return rootView;
		}
	}

	//for receive customer msg from jpush server
		private MessageReceiver mMessageReceiver;
		public static final String MESSAGE_RECEIVED_ACTION = "www.pshow.goldpush.MESSAGE_RECEIVED_ACTION";
		public static final String KEY_TITLE = "title";
		public static final String KEY_MESSAGE = "message";
		public static final String KEY_EXTRAS = "extras";
		
		public void registerMessageReceiver() {
			mMessageReceiver = new MessageReceiver();
			IntentFilter filter = new IntentFilter();
			filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
			filter.addAction(MESSAGE_RECEIVED_ACTION);
			registerReceiver(mMessageReceiver, filter);
		}

		public class MessageReceiver extends BroadcastReceiver {

			@Override
			public void onReceive(Context context, Intent intent) {
				if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
	              String messge = intent.getStringExtra(KEY_MESSAGE);
	              String extras = intent.getStringExtra(KEY_EXTRAS);
	              StringBuilder showMsg = new StringBuilder();
	              showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
//	              if (!ExampleUtil.isEmpty(extras)) {
//	            	  showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//	              }
	              setCostomMsg(showMsg.toString());
				}
			}
		}
		
		private void setCostomMsg(String msg){
			 if (null != msgText) {
				 msgText.setText(msg);
				 msgText.setVisibility(android.view.View.VISIBLE);
	         }
		}
}
