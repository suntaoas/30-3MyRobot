package robot.com.myrobot;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 真心英雄
 * @version 1.0
 * time ：2015-11-17
 * qq: 2286502415
 * 涉及内容：httpclient异步获取数据；josn数据解析；接口传值；随机数组；显示时间；adatper多种布局
 */
public class MainActivity extends Activity implements HttpGetDataListener,View.OnClickListener{
    private HttpData mHttpData;
    private String url="http://www.tuling123.com/openapi/api?key=17191974786e3fa75cfe229024fe9d6a&info=";
    private List<ListData> list;
    private ListView lv;
    private EditText sendText;
    private Button sendBut;
    private String content_string;
    private TestAdapter adapter;
    private String[] welcomeArrays;//欢迎语数组
    private long currentTime;//当前系统时间
    private long oldTime;//过去时间
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化数据
     */
    public void initView(){
        lv = (ListView) findViewById(R.id.lv);
        sendText = (EditText) findViewById(R.id.sendText);
        sendText.setSelection(sendText.getText().length());
        sendBut = (Button) findViewById(R.id.sendBut);
        list = new ArrayList<ListData>();
        sendBut.setOnClickListener(this);
        adapter = new TestAdapter(list,this);
        lv.setAdapter(adapter);
        ListData listData = new ListData(getWellcometips(),ListData.RECEIVER,getTimes());
        list.add(listData);
        adapter.notifyDataSetChanged();//重新绘制界面

    }

    /**
     * 接口回调获得数据
     * @param data
     */
    @Override
    public void getDataUrl(String data) {
        getDataText(data);
    }

    /**
     * 获得json数据
     * @param data
     */
    public void getDataText(String data){
        try {
            JSONObject jo = new JSONObject(data);
            String code = jo.getString("code");
            String text = jo.getString("text");
            ListData listData;
            listData = new ListData(jo.getString("text"),ListData.RECEIVER,getTimes());
            list.add(listData);//实现数据封装
            adapter.notifyDataSetChanged();//接收数据后重新适配
            Log.e("text=========", text);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获得欢迎语资源
     */
    public String getWellcometips(){
        String wellcomTips = null;
        welcomeArrays = this.getResources().getStringArray(R.array.welcome_tips);
        int id = (int) (Math.random()*(welcomeArrays.length-1));
        wellcomTips = welcomeArrays[id];
        return wellcomTips;
    }

    /**
     * 显示时间
     */
    public String getTimes(){
        currentTime = System.currentTimeMillis();//获得当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//格式化日期
        Date data = new Date();
        String str = sdf.format(data);
        if(currentTime-oldTime>=15*1000){//没隔5秒钟返回一下时间
            oldTime = currentTime;
            return str;
        }else{
            return "";
        }
    }

    /**
     * 发送按钮
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v ==sendBut){
            content_string = sendText.getText().toString().trim();
            sendText.setText(null);
            String updataData = content_string.replace(" ","").replace("\r","");
            ListData listData = new ListData(content_string,ListData.SEND,getTimes());
            list.add(listData);
            if(list.size()>300){
                list.remove(0);//当数据大于100条是总是删除第一条
            }
            mHttpData = (HttpData) new HttpData(this).execute(url+updataData);//异步启动URL

        }
    }

}
