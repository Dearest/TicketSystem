package com.compitation.ticketsystem.Activity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.compitation.ticketsystem.R;
import com.compitation.ticketsystem.Idispatch.IUploadDispatch;
import com.compitation.ticketsystem.dispatchImpl.UploadDispatchImpl;
import com.compitation.ticketsystem.scan.AssetsResource;
import com.compitation.ticketsystem.scan.ColorKMeans;
import com.compitation.ticketsystem.scan.ImageTools;
import com.compitation.ticketsystem.scan.Oritenation;
import com.compitation.ticketsystem.scan.PlateNumberGroup;
import com.compitation.ticketsystem.scan.RecEachCharInMinDis;
import com.compitation.ticketsystem.scan.SegInEachChar;
import com.compitation.ticketsystem.utils.SystemContent;
import com.comtipation.ticketsystem.model.Ticket;

@SuppressLint("SimpleDateFormat")
public class UploadActivity extends Activity {
	private ArrayAdapter<String> adapter;
	private List<String> list = new ArrayList<String>();
	private EditText time, address, platenumber;
	private Spinner type;
	private ProgressDialog progressDialog;
	private Button btn_upload, btn_positing, btn_platenumber;
	private String ticket_type, car_num, position, ticket_time;
	private IUploadDispatch uploadDispatch;
	private Handler handler;
	private String addressString;
	private LocationClient locationClient = null;
	private static final int UPDATE_TIME = 5000;
	private static final int PHOTO_CAPTURE = 0x11;// 拍照
	private static final int PHOTO_RESULT = 0x12;// 裁剪结果
	private static String TESSBASE_PATH = "";
	private static final String DEFAULT_LANGUAGE = "eng";
	private static final int TAKE_PICTURE = 0;
	private int REQUEST_CODE = 0;
	private Bitmap bitmap = null;
	private Bitmap newBitmap = null;
	private Bitmap curbitmap = null;
	private Bitmap[] bitmaps = null;
	private String cph = null;
	private static final int SCALE = 5;
	private Ticket ticket;
	private SharedPreferences mySharedPreferences;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		Looper looper = Looper.myLooper();
		handler = new UploadHandler(looper);
		uploadDispatch = new UploadDispatchImpl();
		mySharedPreferences = getSharedPreferences(
				SystemContent.PREFERNCE_NAME, Activity.MODE_PRIVATE);
		type = (Spinner) findViewById(R.id.type);
		time = (EditText) findViewById(R.id.time);
		address = (EditText) findViewById(R.id.address);
		platenumber = (EditText) findViewById(R.id.et_platenumber_);// 车牌号的输入框
		btn_platenumber = (Button) findViewById(R.id.bt_platenumber);// 车牌号的button
		btn_upload = (Button) findViewById(R.id.bt_upload);// 上传的button
		btn_positing = (Button) findViewById(R.id.positing);

		locationClient = new LocationClient(getApplicationContext());
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 是否打开GPS
		option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
		option.setProdName("LocationDemo"); // 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		option.setScanSpan(UPDATE_TIME);
		option.setAddrType("all"); // 设置定时定位的时间间隔。单位毫秒
		locationClient.setLocOption(option);
		locationClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				Log.i("Flag", "开始定位");
				if (location == null) {
					return;
				}
				StringBuffer stringBuffer = new StringBuffer(256);
				stringBuffer.append(location.getAddrStr());
				 address.setText(stringBuffer.toString());
				Log.i("Flag", "定位 ：" + stringBuffer.toString());
			}

			@Override
			public void onReceivePoi(BDLocation location) {
			}
		});
		time.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
				.format(new Date()));
	

		TicktTypeSpinner();

		btn_positing.setOnClickListener(new OnClickListener() {
			// 定位
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Falg", "点击定位");
				if (locationClient == null) {
					return;
				}
				if (locationClient.isStarted()) {
					btn_positing.setText("定  位");
					locationClient.stop();
				} else {
					btn_positing.setText("停  止");
					locationClient.start();
					locationClient.requestLocation();
				}
			}
		});

		btn_platenumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri imageUri = null;
				String fileName = null;
				Intent openCameraIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				REQUEST_CODE = TAKE_PICTURE;
				fileName = "image.jpg";
				imageUri = Uri.fromFile(new File(Environment
						.getExternalStorageDirectory(), fileName));
				openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(openCameraIntent, REQUEST_CODE);
			}
		});

		btn_upload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				position = address.getText().toString(); // 获得罚单地址
				car_num = platenumber.getText().toString(); // 获得车牌号
				ticket_time = time.getText().toString().replace(" ", "+"); // 获得罚单时间

				if (!TextUtils.isEmpty(position) || TextUtils.isEmpty(car_num)
						|| TextUtils.isEmpty(ticket_time)) {
					if (isNetworkConnected()) {
						ticket  = new Ticket();
						ticket.setUserId(mySharedPreferences.getString("userId", ""));
						ticket.setTime(ticket_time);
						ticket.setAddress(position);
						ticket.setCarNum(car_num);
						ticket.setIrregularity(ticket_type);
						uploadDispatch.upload(handler, ticket);
					} else {
						Toast.makeText(UploadActivity.this,
								"无法连接网络，请检查网络连接后再试", Toast.LENGTH_LONG).show();
					}
				}
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


	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (locationClient != null && locationClient.isStarted()) {
			locationClient.stop();
			locationClient = null;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Config.ARGB_8888;
		bitmap = BitmapFactory.decodeFile(
				Environment.getExternalStorageDirectory() + "/image.jpg",
				options);
		newBitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / SCALE,
				bitmap.getHeight() / SCALE);
		bitmap.recycle();
		// ImageTools.savePhotoToSDCard(
		// newBitmap,
		// Environment.getExternalStorageDirectory().getAbsolutePath(),
		// String.valueOf(System.currentTimeMillis()));
		progressDialog = ProgressDialog.show(UploadActivity.this, "Loading...",
				"Please wait...");
		new Thread() {

			@Override
			public void run() {
				try {
					// TODO Auto-generated method stub
					curbitmap = ColorKMeans.Math(newBitmap);
					curbitmap = Oritenation.Math(curbitmap, newBitmap);

					if (PlateNumberGroup.AlreadyChecked) {
						bitmaps = SegInEachChar.Math(curbitmap);

						AssetsResource.context = UploadActivity.this;
						cph = RecEachCharInMinDis.Math(bitmaps);
						try {
							cph = new String(cph.getBytes("utf-8"));
							cph = cph.substring(0, cph.indexOf("["));
							Log.i("Flag", "Cph :" + cph);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handler.sendEmptyMessage(22);
					} else {
						handler.sendEmptyMessage(33);
					}
				} catch (Exception e) {
					// TODO: handle exception
					handler.sendEmptyMessage(33);
				}
			}

		}.start();
	}
	class UploadHandler extends Handler {
		public UploadHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(UploadActivity.this,"上传成功", Toast.LENGTH_LONG).show();
				break;
			case 2:
				Toast.makeText(UploadActivity.this,"上传失败", Toast.LENGTH_LONG).show();
				break;
			case 22:
				platenumber.setText(cph);
				progressDialog.dismiss();
				Toast.makeText(UploadActivity.this,"解析成功", Toast.LENGTH_LONG).show();
				break;
			case 33:
				progressDialog.dismiss();
				Toast.makeText(UploadActivity.this,"你拍摄的照片无法解析，请手动输入", Toast.LENGTH_LONG).show();
				break;
			default:
				Toast.makeText(UploadActivity.this,"服务器出错，请稍候再试", Toast.LENGTH_LONG).show();
				break;
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
