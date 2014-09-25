package com.compitation.ticketsystem.Activity;

import java.util.ArrayList;
import java.util.List;

import com.compitation.ticketsystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Register extends Activity{
	private EditText username;
	private EditText password;
	private EditText onfirmpassword;
	private EditText answer;
	private EditText carnum;
	private Spinner security_question;
	private Button submit;
	private RadioGroup sex;
	
	private List<String> questionlist = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		username = (EditText)findViewById(R.id.re_account);
		password = (EditText)findViewById(R.id.password);
		onfirmpassword = (EditText)findViewById(R.id.re_password_ag);
		carnum = (EditText)findViewById(R.id.car_account);
		answer = (EditText)findViewById(R.id.answer);
		security_question = (Spinner)findViewById(R.id.security_question);
		submit = (Button)findViewById(R.id.register_ok);
		sex = (RadioGroup)this.findViewById(R.id.sex);
		
		questionType();// 密保问题下拉菜单
	}
	/**
	 * 设置密保问题下拉菜单
	 */
	private void questionType() {
		// TODO Auto-generated method stub
		questionlist.add("您最喜欢的一组数字是？");
		questionlist.add("您的出生地是？");
		questionlist.add("您的学号（或工号）是？");
		questionlist.add("您父亲的姓名是？");
		questionlist.add("您母亲的姓名是？");
		questionlist.add("您的小学校名是？");
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, questionlist);
        //设置下拉菜单样式
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		security_question.setAdapter(adapter);
		security_question.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int position, long arg3) {
				// TODO Auto-generated method stub
				parent.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				arg0.setVisibility(View.VISIBLE);
			}
			
		});
		
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				confirm();
			}
			
		});
	}
	protected void confirm() {
		// TODO Auto-generated method stub
		
	}

}
