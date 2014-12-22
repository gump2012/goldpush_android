package com.pshow.goldpush;

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
				        		String str = "设备id:";
				        		str +=		Myapp.getInstance().name;
				        		str += "\n";
				        		str += "手机号:";
				        		str += mytext.getText();
				        		new AlertDialog.Builder(getActivity()) 
				                .setMessage(str)  
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

}
