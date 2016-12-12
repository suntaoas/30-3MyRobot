package robot.com.myrobot;

/**
 * Created by Huzhiwei on 2015/11/16.
 * 数据封装类
 */
public class ListData {
    public static final int SEND =1;//用户自己
    public static final int RECEIVER =2;//is robot
    private String content;
    private int flag;
    private String times;
    public ListData(String content,int flag,String time) {

        setContent(content);
        setFlag(flag);
        setTimes(time);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
