package robot.com.myrobot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Huzhiwei on 2015/11/16.
 * listView适配器
 */
public class TestAdapter extends BaseAdapter {
    private List<ListData> lists;
    private Context mContext;

    public TestAdapter(List<ListData> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (lists.get(position).getFlag() == ListData.RECEIVER) {
            convertView = inflater.inflate(R.layout.leftitem, null);
        } else {
            convertView = inflater.inflate(R.layout.rightitem, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tv);//聊天信息
        TextView time = (TextView) convertView.findViewById(R.id.time);//显示时间

        tv.setText(lists.get(position).getContent().replace("<br>", "\r\n"));
        if (lists.get(position).getTimes().equalsIgnoreCase("")) {
            time.setHeight(0);
        } else {
            time.setText(lists.get(position).getTimes());
        }
        return convertView;
    }
}
















