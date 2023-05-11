package biao.community.controller;


import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port3_6.JsonValue;
import biao.community.information.port3_6.Result;
import biao.community.service.SSarchDealKeywords;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CSarchDealKeywords {

    @Autowired
    SSarchDealKeywords sSarchDealKeywords;

    @Autowired
    Examine examine;

    //搜索商场帖子
    @RequestMapping("sarchDealKeywords")
    public JSONObject sarchDealKeywords(@RequestHeader("sKey") String sKey, @RequestBody String JsonValue/**postJson是json字符串**/){
        //json转class
        JSONObject jsonObject = JSONObject.parseObject(JsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();
        //传入数据是否正常
        boolean normal = true;
        if(!DESUtils.checkSKey(MD5.Md5Lower32(JsonValue),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
            normal=false;
        }else {

            //检查数据是佛正确
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                normal = false;
                result.setCode(200);
                result.setErrorMessage("请检查u_id");
            }


            if(jsonValueClass.getG_class()!=null){
                if(jsonValueClass.getG_class().length()==0){
                    jsonValueClass.setG_class(null);
                }else if(examine.exGClass(jsonValueClass.getG_class())){
                    normal = false;
                    result.setCode(200);
                    result.setErrorMessage("请检查g_class");
                }
            }

            if(jsonValueClass.getMinPrice()!=-1&&jsonValueClass.getMaxPrice()!=-1){
                if(jsonValueClass.getMaxPrice()<=jsonValueClass.getMinPrice()){
                    normal = false;
                    result.setCode(200);
                    result.setErrorMessage("请检查价格区间");
                }
            }

            if(jsonValueClass.getU_city()!=null) {
                if (jsonValueClass.getU_city().length() == 0) {
                    jsonValueClass.setU_city(null);
                } else if (examine.exCity(jsonValueClass.getU_city())) {
                    normal = false;
                    result.setCode(200);
                    result.setErrorMessage("请检查校区");
                }
            }

            if(jsonValueClass.getSize() > 20 && jsonValueClass.getSize() <= 0){
                normal = false;
                result.setCode(200);
                result.setErrorMessage("请检查size");
            }

        }




        //数据正常，开始查询
        if(normal){
            sSarchDealKeywords.sarchDealKeywords(jsonValueClass,result);
            result.setCode(200);
        }

        Information information = new Information(Tool.classToJson(result));

        return Tool.classToJson(information);
    }

}
