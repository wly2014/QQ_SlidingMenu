/**
 * Copyright © 2014 All rights reserved.
 *
 * @Title: SlidingPaneContentFragment.java
 * @Prject: SlidingPane
 * @Package: com.example.slidingpane
 * @Description: TODO
 * @author: raot  719055805@qq.com
 * @date: 2014年9月5日 上午10:44:01 
 * @version: V1.0
 */
package com.wly.sliding_panel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * @ClassName: SlidingPaneContentFragment
 * @Description: TODO
 * @author: raot 719055805@qq.com
 * @date: 2014年9月5日 上午10:44:01
 */
public class ContentFragment extends Fragment {
    private View currentView;

    public void setCurrentViewPararms(LayoutParams layoutParams) {
        currentView.setLayoutParams(layoutParams);
    }

    public LayoutParams getCurrentViewParams() {
        return (LayoutParams) currentView.getLayoutParams();
    }
    public View getCurrentView() {
        return currentView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        currentView = inflater.inflate(R.layout.slidingpane_content_layout,
                container, false);

        ListView listView = (ListView) currentView.findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, new String[]{"qqqqqqqqq", "222222222222", "eeeeeeeeeeeeee"}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        return currentView;
    }

}
