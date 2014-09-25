package com.compitation.ticketsystem.Activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.compitation.ticketsystem.R;

@SuppressLint("SimpleDateFormat")
public class UploadActivity extends Activity {
	private ArrayAdapter<String> adapter;
	private List<String> list = new ArrayList<String>();
	private EditText time, address, platenumber;
	private Spinner type;
	private Button btn_upload, btn_positing, btn_platenumber;
	private String ticket_type,car_num,position,ticket_time;
	private String[] timearr = new String[2];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		
		type = (Spinner)findViewById(R.id.type);
		time = (EditText)findViewById(R.id.time);
		address = (EditText)findViewById(R.id.address);
		platenumber = (EditText)findViewById(R.id.et_platenumber_);// 车牌号的输入框
		btn_platenumber = (Button)findViewById(R.id.bt_platenumber);// 车牌号的button
		btn_upload = (Button)findViewById(R.id.bt_upload);// 上传的button
		btn_positing = (Button)findViewById(R.id.positing);
		
		time.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		timearr = time.getText().toString().split(" ");
		
		TicktTypeSpinner();
		
		btn_positing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btn_platenumber.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
        btn_upload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Judge();
			}
		});
	}

	private void TicktTypeSpinner() {
		// TODO Auto-generated method stub
		list.add("违规停车");
		list.add("违反禁令标志指示");
		list.add("未放置保险标志");
		list.add("未放置检验合格标志");
		
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// 设置下拉菜单样式
		type.setAdapter(adapter);
		type.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long arg3) {
				ticket_type = adapter.getItem(position);// 获取到罚单类型
				parent.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				arg0.setVisibility(View.VISIBLE);
			}
		});
	}

	protected void Judge() {
		// TODO Auto-generated method stub
		position = address.getText().toString(); //获得罚单地址
		car_num = platenumber.getText().toString(); //获得车牌号
		ticket_time = time.getText().toString(); //获得罚单时间
		
		if(!TextUtils.isEmpty(position)||TextUtils.isEmpty(car_num)||TextUtils.isEmpty(ticket_time)){
				if (isNetworkConnected()) {
					
				} else {
					Toast.makeText(UploadActivity.this, "无法连接网络，请检查网络连接后再试",
							Toast.LENGTH_LONG).show();
				}
		}
		
	}

	/**
	 * 判断是否连接到网络
	 * 
	 * @return true 能连接到网络 false 不能连接到网络
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.isConnectedOrConnecting();
		boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.isConnectedOrConnecting();
		if (wifi | internet) {
			return true;
		} else {
			return false;
		}

	}
	

}

