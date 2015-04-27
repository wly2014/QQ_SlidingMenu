package com.wly.water_fall_listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {


	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {//拦截
		return true;
	}

//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		return super.dispatchTouchEvent(ev);
//	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		int width=getWidth()/getChildCount();
		int height = getHeight();
		int count=getChildCount();
		
		float eventX = event.getX();
		
		if (eventX<width){	//  listView
			event.setLocation(width/2, event.getY());       //传给子View之前，重新设置坐标，一般若子View的组件仅有一个（如本例子中的ImageView），应该可以不设置
			getChildAt(0).onTouchEvent(event);        //拦截事件后，自己再重新分配事件。//使用dispatch好像是一样子的
			return true;            //消费完了
			
		} else if (eventX > width && eventX < 2 * width) { //listView
			float eventY = event.getY();
			if (eventY < height / 2) {                  //点击的是中间的listView的上部时
				event.setLocation(width / 2, event.getY());
				for (int i = 0; i < count; i++) {       //将事件分发给所有的listView
					View child = getChildAt(i);
					try {
						child.onTouchEvent(event);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				return true;
			} else if (eventY > height / 2) {           //点击的是中间的listView的下半部时
				event.setLocation(width / 2, event.getY());
				try {
					getChildAt(1).onTouchEvent(event);//仅将事件分发给中间的listView
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}
		}else if (eventX>2*width){                      //点击了第三个listview的位置
			event.setLocation(width/2, event.getY());
			getChildAt(2).onTouchEvent(event);
			return true;
		}
		
		return true;
	}
	
}
