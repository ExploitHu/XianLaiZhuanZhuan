package biao.im.tool;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tool {
    static private String classToString(Object object) {
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
}
