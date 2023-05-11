package biao.community;

import biao.community.dao.Examine;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TestJiaMi {

    @Autowired
    Examine examine;

    private static int aa = 0;

    @RequestMapping("/testMD5")
    public JSONObject test(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/){

        String md5 = MD5.Md5Lower32(JsonValue);
        System.out.println(md5);

        JSONObject result = new JSONObject();

        result.put("结果",DESUtilsTemp.checkSKey(md5, sKey,result));
        result.put("正确的md5值（32位小写）", md5);


        return result;

    }





}
