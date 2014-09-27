package com.compitation.ticketsystem.Activity;

import com.compitation.ticketsystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ForgetAndRegister extends Activity implements OnClickListener {
	private Button btn_forget, btn_register, btn_cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_and_register);
		btn_forget = (Button) this.findViewById(R.id.btn_forget);
		btn_register = (Button) this.findViewById(R.id.btn_register);
		btn_cancel = (Button) this.findViewById(R.id.btn_cancel);

		btn_cancel.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		btn_forget.setOnClickListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_forget:
			btn_forget.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.shape_bg_change));
			startActivity(new Intent(ForgetAndRegister.this,
					ForgetPasswordActivity.class));
			break;
		case R.id.btn_register:
			btn_register.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.shape_bg_change));
			startActivity(new Intent(ForgetAndRegister.this,
					RegisterActivity.class));
			break;
		case R.id.btn_cancel:

			btn_cancel.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.shape_bg_change));
			finish();
			break;
		default:
			break;
		}
		finish();
	}

}
