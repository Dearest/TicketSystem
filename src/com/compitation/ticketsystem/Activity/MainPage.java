package com.compitation.ticketsystem.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.compitation.ticketsystem.R;
import com.compitation.ticketsystem.TicketDetail;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;

@SuppressWarnings("deprecation")
public class MainPage extends ActivityGroup {
	private Context context = null;
    private LocalActivityManager manager = null;
    private ViewPager pager = null;
    private List<View> dots; // 图片标题正文的那些点
    private ArrayList<View> list;
    private TextView tv_title;
    private int currentItem = 0; // 当前图片的索引号
    private TextView get_num;
    private TextView post_num;
    private String s_get_num;
    private String s_post_num;
    private ListView thanks;
    
    private ScheduledExecutorService scheduledExecutorService;
    
 // 切换当前显示的图片
 	private Handler handler = new Handler() {
 		@SuppressLint("HandlerLeak")
		public void handleMessage(android.os.Message msg) {
 			pager.setCurrentItem(currentItem);// 切换当前显示的图片
 		};
 	};

   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.main_page);
		
		context = MainPage.this;
		manager = getLocalActivityManager();
		manager.dispatchCreate(savedInstanceState);
		
		
		
		dots = new ArrayList<View>();
		dots.add(findViewById(R.id.v_dot0));
		dots.add(findViewById(R.id.v_dot1));
		dots.add(findViewById(R.id.v_dot2));
		
		pager = (ViewPager) findViewById(R.id.vp);
		list = new ArrayList<View>();
		Intent intent = new Intent(context,View1.class);
		list.add(getView("A",intent));
		Intent intent2 = new Intent(context,View2.class);
		list.add(getView("B", intent2));
		Intent intent3 = new Intent(context,View3.class);
		list.add(getView("C", intent3));
		
		pager.setAdapter(new MyAdapter());
		pager.setCurrentItem(0);
		pager.setOnPageChangeListener(new MyPageChangeListener());
		
		get_num = (TextView)findViewById(R.id.get_num);
		post_num = (TextView)findViewById(R.id.send_num);
		thanks = (ListView)findViewById(R.id.thanks);
		
		get_num.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainPage.this,TicketDetail.class);
				startActivity(intent);
				
			}
		});
	}
	
	/**
	 * 通过activity获取视图
	 * @param id
	 * @param intent
	 * @return
	 */
	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}

	
	@Override
	protected void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
		super.onStart();
	}

	/*@Override
	protected void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}*/
	
	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (pager) {
				System.out.println("currentItem: " + currentItem);
				DisplayMetrics dm = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(dm);
				int screenW = dm.widthPixels;// 获取分辨率宽度
				currentItem = (currentItem + 1) % 3;
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}
	
	
	
	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(list.get(arg1));
			return list.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}
	}
	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

}


