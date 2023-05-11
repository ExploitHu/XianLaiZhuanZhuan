package biao.community.tool;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;


public class Tool {

    static public String classToString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "defeated";
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return result;
        }
        return result;

    }

    static public JSONObject classToJson(Object object) {

        JSONObject jsonObject = JSONObject.parseObject(Tool.classToString(object));

        return jsonObject;
    }

    /***
     * 输出一个sum位的随机数字字母
     * @param sum 输出的随机数字字母一共几位
     * @return
     */
    static public String randomNumberAlphabet(int sum) {

        String result = "";
        for (int i = 0; i < sum; i++) {
            int temp = (int) (Math.random() * 100) % 36;
            if (temp < 10) {
                result = result + String.valueOf(temp);
            } else {
                result = result + (char) (temp + 55);
            }
        }

        return result;
    }

}
