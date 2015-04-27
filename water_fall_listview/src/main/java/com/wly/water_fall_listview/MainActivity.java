package com.wly.water_fall_listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lv1;
	private ListView lv2;
	private ListView lv3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv1 = (ListView) findViewById(R.id.lv1);
		lv2 = (ListView) findViewById(R.id.lv2);
		lv3 = (ListView) findViewById(R.id.lv3);

		try {
			lv1.setAdapter(new MyAdapter1());
			lv2.setAdapter(new MyAdapter1());
			lv3.setAdapter(new MyAdapter1());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private int ids[] = new int[] { R.mipmap.default1, R.mipmap.girl1,
			R.mipmap.girl2, R.mipmap.girl3 };

	class MyAdapter1 extends BaseAdapter {

		@Override
		public int getCount() {
			return 3000;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ImageView iv = (ImageView) View.inflate(getApplicationContext(),
					R.layout.lv_item, null);
			int resId = (int) (Math.random() * 4);
			iv.setImageResource(ids[resId]);
			return iv;
		}
	}
}
