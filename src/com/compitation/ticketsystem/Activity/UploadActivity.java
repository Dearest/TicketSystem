package com.compitation.ticketsystem.Activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.compitation.ticketsystem.R;

public class UploadActivity extends Activity {
	private EditText time, address, platenumber;
	private Spinner type;
	private Button btn_upload, btn_positing, btn_platenumber;
	private String typeString;
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
		
		btn_upload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	

}
