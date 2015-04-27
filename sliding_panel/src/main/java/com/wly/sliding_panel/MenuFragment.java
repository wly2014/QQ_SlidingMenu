/**   
 * Copyright © 2014 All rights reserved.
 * 
 * @Title: SlidingPaneMenuFragment.java 
 * @Prject: SlidingPane
 * @Package: com.example.slidingpane 
 * @Description: TODO
 * @author: raot  719055805@qq.com
 * @date: 2014年9月5日 上午10:42:07 
 * @version: V1.0   
 */
package com.wly.sliding_panel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

/**
 * @ClassName: SlidingPaneMenuFragment
 * @Description: TODO
 * @author: raot 719055805@qq.com
 * @date: 2014年9月5日 上午10:42:07
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

	private View currentView;
	private Button bt1, bt2, bt3, bt4, bt5, bt6;

	public View getCurrentView() {
		return currentView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		currentView = inflater.inflate(R.layout.slidingpane_menu_layout,
				container, false);
		bt1 = (Button) currentView.findViewById(R.id.bt1);
		bt2 = (Button) currentView.findViewById(R.id.bt2);
		bt3 = (Button) currentView.findViewById(R.id.bt3);
		bt4 = (Button) currentView.findViewById(R.id.bt4);
		bt5 = (Button) currentView.findViewById(R.id.bt5);
		bt6 = (Button) currentView.findViewById(R.id.bt6);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
		bt5.setOnClickListener(this);
		bt6.setOnClickListener(this);
		return currentView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		((MainActivity) getActivity()).getSlidingPaneLayout().closePane();
	}
}
