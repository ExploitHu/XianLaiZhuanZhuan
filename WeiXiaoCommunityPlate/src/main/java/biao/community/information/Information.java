package biao.community.information;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;

import java.util.Map;

public class Information {
    private Object data;

    //初始化函数
    public Information() {
    }

    public Information(Map data) {
        this.data = data;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
