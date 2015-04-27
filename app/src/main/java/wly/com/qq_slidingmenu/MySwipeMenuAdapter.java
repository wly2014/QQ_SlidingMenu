package wly.com.qq_slidingmenu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/10/27.
 */
public class MySwipeMenuAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;

    public MySwipeMenuAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        View view;
//        ViewHolder holder;
//        if (convertView==null){
//            view=View.inflate(context,R.layout.swipe_menu_list_item,null);
//            holder=new ViewHolder();
//
//            holder.number= (TextView) view.findViewById(R.id.tv_number);
//            holder.name= (TextView) view.findViewById(R.id.tv_name);
//            view.setTag(holder);
//        }else {
//
//            view=convertView;
//            holder= (ViewHolder) view.getTag();
//
//        }
//        holder.number.setText(list.get(position).get("number").toString());
//        holder.name.setText(list.get(position).get("name").toString());
//
//        return view;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context,
                    R.layout.swipe_menu_list_item, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.name.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView name;

        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(this);
        }
    }


//    private class ViewHolder {
//        private TextView number;
//        private TextView name;
//
//    }


}
