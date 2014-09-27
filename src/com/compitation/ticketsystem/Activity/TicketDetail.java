package com.compitation.ticketsystem.Activity;

import com.compitation.ticketsystem.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;

public class TicketDetail extends Activity {
	private ImageButton btn_return;
	private Button next;
	
	private TextView time;
	private TextView addr;
	private TextView type;
	
	private String s_time;
	private String s_addr;
	private String s_type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticket_detail);
		
		btn_return = (ImageButton)findViewById(R.id.btn_back);//返回按钮
		next = (Button)findViewById(R.id.next);
		time = (TextView)findViewById(R.id.time); //罚单时间
		addr = (TextView)findViewById(R.id.address);//罚单地点
		type = (TextView)findViewById(R.id.type);//罚单类型
		
		btn_return.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}

}
