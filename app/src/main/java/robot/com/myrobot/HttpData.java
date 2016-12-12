package robot.com.myrobot;


import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by Huzhiwei on 2015/11/15.
 * HttpClient网络框架异步读取数据
 */
public class HttpData extends AsyncTask<String,Integer,String> {//url;更新进度;返回值


    private HttpClient mHttpClient;
    private HttpGet mHttpGet;//get请求方式
    private HttpResponse mHttpResponse;
    private HttpEntity mHttpEntity;//返回的内容
    private InputStream in;
    private HttpGetDataListener listener;//接口，用于数据交互

    public HttpData(HttpGetDataListener httpGetDataListener) {
        this.listener = httpGetDataListener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            mHttpClient = new DefaultHttpClient();
            mHttpGet = new HttpGet(params[0]);//url
            mHttpResponse = mHttpClient.execute(mHttpGet);
            mHttpEntity = mHttpResponse.getEntity();
            in = mHttpEntity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine())!=null){
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.getDataUrl(s);//用接口传递数据
        Log.e("9999999999999999999",s);
    }
}
