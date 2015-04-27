package wly.com.qq_slidingmenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuAdapter;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/21.
 */
public class SignFragment extends Fragment {


    private SwipeMenuListView swipeMenuListView;
    private MySwipeMenuAdapter swipeMenuAdapter;
    private List<String> list;

    private Context context;

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        context = activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign, container, false);


        list = new ArrayList<String>();
        list.add("wly");
        list.add("wly2014");
        list.add("wly");
        list.add("wly2014");
        list.add("wly");
        list.add("wly2014");
        list.add("wly");
        list.add("wly2014");
        list.add("wly");
        list.add("wly2014");
        list.add("wly");
        list.add("wly2014");
        list.add("wly");
        list.add("wly2014");


        swipeMenuListView = (SwipeMenuListView) view.findViewById(R.id.list_view);
        swipeMenuAdapter = new MySwipeMenuAdapter(context, list);
        swipeMenuListView.setAdapter(swipeMenuAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem item = new SwipeMenuItem(context);

                item.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                item.setWidth(200);
                item.setTitle("签到");
                item.setTitleSize(25);
                item.setTitleColor(Color.WHITE);
                menu.addMenuItem(item);

            }
        };
        swipeMenuListView.setMenuCreator(creator);
        return view;
    }


}
