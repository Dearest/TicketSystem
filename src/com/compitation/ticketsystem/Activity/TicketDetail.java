package com.compitation.ticketsystem.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.compitation.ticketsystem.R;
import com.compitation.ticketsystem.Idispatch.IMainPageDispatch;
import com.compitation.ticketsystem.dispatchImpl.MainPageDispatchImpl;
import com.compitation.ticketsystem.utils.SystemContent;
import com.comtipation.ticketsystem.model.Ticket;

public class TicketDetail extends Activity {
	private ImageButton btn_return;
	private Button next;

	private TextView time;
	private TextView addr;
	private TextView type;

	private IMainPageDispatch mainPageDispatch;
	private Handler handler;
	private List<Ticket> tickets;
	private SharedPreferences mySharedPreferences;
	private int flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticket_detail);
		mainPageDispatch = new MainPageDispatchImpl();
		mySharedPreferences = getSharedPreferences(SystemContent.PREFERNCE_NAME,Activity.MODE_PRIVATE);
		btn_return = (ImageButton) findViewById(R.id.btn_back);// 返回按钮
		next = (Button) findViewById(R.id.next);
		time = (TextView) findViewById(R.id.time); // 罚单时间
		addr = (TextView) findViewById(R.id.address);// 罚单地点
		type = (TextView) findViewById(R.id.type);// 罚单类型
		tickets = new ArrayList<Ticket>();
		handler = new Handler(){
			@SuppressWarnings("unchecked")
			@Override
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					tickets = (ArrayList<Ticket>)msg.obj;
					flag = tickets.size();
					break;
				case 2:
					Toast.makeText(TicketDetail.this, "今天沒有罚单", Toast.LENGTH_LONG).show();
					break;
				default:
					break;
				}
			}
		};
		
		mainPageDispatch.getDetail(handler, mySharedPreferences.getString("userId", ""));
		if (!tickets.isEmpty()) {
			time.setText(tickets.get(flag).getUploadTime());
			addr.setText(tickets.get(flag).getAddress());
			type.setText(tickets.get(flag).getIrregularity());
		}
		
		
		btn_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(TicketDetail.this,
						ViewPagerActivity.class));
				finish();
			}
		});

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				flag -=1; 
				if (flag>=0) {
					time.setText(tickets.get(flag).getUploadTime());
					addr.setText(tickets.get(flag).getAddress());
					type.setText(tickets.get(flag).getIrregularity());
				}
			}
		});

	}

}
