package robot.com.myrobot;

import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by Huzhiwei on 2015/11/17.
 * 通过xUtils获得数据
 */
public class Nomer {
    private String url;
    private HttpGetDataListener listener;//接口，用于数据交互

    public Nomer(String url, HttpGetDataListener listener) {
        this.url = url;
        this.listener = listener;
    }

    public void x(){
        HttpUtils httpUtils = new HttpUtils(5000);

        httpUtils.send(HttpRequest.HttpMethod.GET,
                url,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        System.out.println("开始请求");
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        System.out.println("正在加载：共" + total + "个字节，当前：" + current);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> stringResponseInfo) {
                        String r = stringResponseInfo.result;

                        System.out.println(r);
                        Log.e("-----------------", r);

                        listener.getDataUrl(r);//用接口传递数据

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        System.out.println("请求失败");
                    }
                });
    }
}
