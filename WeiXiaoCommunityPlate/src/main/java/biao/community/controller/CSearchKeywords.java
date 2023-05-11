package biao.community.controller;


import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port2_1and3_1.Result;
import biao.community.information.port2_7.JsonValue;
import biao.community.service.SSearchKeywords;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CSearchKeywords {

    @Autowired
    SSearchKeywords sSearchKeywords;

    @Autowired
    Examine examine;

    //2.7  搜索功能
    //功能：用户输入要搜索的关键词来搜素社区帖子
    @RequestMapping("/sarchKeywords")
    public JSONObject searchKeywords(@RequestHeader("sKey") String sKey, @RequestBody String jsonValue/**postJson是json字符串**/){

        Result result = new Result();
        JSONObject jsonObject = JSONObject.parseObject(jsonValue);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        //传入数据是否正常
        boolean normal = true;

        if(!DESUtils.checkSKey(MD5.Md5Lower32(jsonValue),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
            normal = false;
        }else {
            //检查数据是佛正确
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                normal = false;
                result.setCode(200);
                result.setErrorMessage("请检查u_id");
            }

            if(jsonValueClass.getSize()<=0){
                normal = false;
                result.setCode(200);
                result.setErrorMessage("请检查size");
            }

            if(jsonValueClass.getBs_id()!=null){
                if(jsonValueClass.getBs_id().length()==0){
                    jsonValueClass.setBs_id(null);
                }else if(examine.exBsId(jsonValueClass.getBs_id())){
                    normal = false;
                    result.setCode(200);
                    result.setErrorMessage("请检查bs_id");
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
            sSearchKeywords.searchKeywords(jsonValueClass,result);
            result.setCode(200);
        }

        Information information = new Information(Tool.classToJson(result));

        return Tool.classToJson(information);

    }
}
