package biao.community;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@CrossOrigin
@RestController
public class Test  {

    @Autowired
    biao.community.dao.DTest test;

    @RequestMapping("/test")
    //public JSONObject test(@RequestHeader("sKey") String sKey,@RequestBody String JsonValue/**postJson是json字符串**/){
    public String test(){
        /*
        String md5 = MD5.Md5Lower32(JsonValue);
        System.out.println(md5);
        //String str = "20220411190256";
        //byte[] result = DESUtils.encrypt(str.getBytes(), "A2C7ED35E05A1B33");


        //File dir = new File();

        JSONObject result = new JSONObject();

        result.put("结果",DESUtilsTemp.checkSKey(md5, sKey,result));
        result.put("正确的md5值（32位小写）", md5);



        return result;



        for (int i = 10000060;i <= 10000134;i++){
            test.selectB(i,randInt(2,132));
        }*/
        return "chenggong";
    }


    @RequestMapping("/setToBeRead")
    public String setToBeRead(@RequestBody String message/**postJson是json字符串**/){
        JSONObject jsonObject = JSONObject.parseObject(message);

        int b =  (int)jsonObject.get("b");
        int g =  (int)jsonObject.get("g");
        int u_id =  (int)jsonObject.get("u_id");

        test.setToBeRead(u_id,b,g);

        return "yes";

    }

    private int randInt(int min,int max){
        Random rd = new Random();

        int temp = rd.nextInt();

        return  (temp>0?temp:temp*-1) %(max+1-min) + min;

    }

}
