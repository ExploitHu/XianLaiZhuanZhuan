package biao.community.controller;

import biao.community.dao.Examine;
import biao.community.information.Information;
import biao.community.information.port2_5.CommentInformation;
import biao.community.information.port3_4.Result;
import biao.community.information.port3_4.JsonValue;
import biao.community.service.SSetComment;
import biao.community.tool.DESUtils;
import biao.community.tool.MD5;
import biao.community.tool.Tool;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CSetComment {

    @Autowired
    SSetComment sSetComment;

    @Autowired
    Examine examine;

    /**
     * 接口2.5写入新的评论
     * @param commentPostJson 新评论内容
     * @return 成功写入内容
     */
    @RequestMapping("/setBComment")
    public JSONObject setBComment(@RequestHeader("sKey") String sKey, @RequestBody String commentPostJson/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(commentPostJson);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        CommentInformation commentInformation = JSONObject.toJavaObject(jsonObject1, CommentInformation.class);

        biao.community.information.port2_5.Result result = new biao.community.information.port2_5.Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(commentPostJson),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
        }else {
            if(commentInformation.getU_id()!=null&&commentInformation.getU_id().length()!=0){
                if(examine.exUId(Integer.parseInt(commentInformation.getU_id()))){
                    result.setErrorMessage("请检查u_id");
                }
            }
            if(examine.exUId(Integer.parseInt(commentInformation.getU_id0()))){
                result.setErrorMessage("请检查u_id0");
            }
            if(examine.exBtId(Integer.parseInt(commentInformation.getBt_id()))){
                result.setErrorMessage("请检查bt_id");
            }
            if(commentInformation.getBtc_com()==null||commentInformation.getBtc_com().length()==0){
                result.setErrorMessage("请检查btc_com");
            }
        }

        if(result.getErrorMessage().equals("success")){
            result.setCode(200);
            result.setBt_id(commentInformation.getBt_id());
            result.setBtc_id(commentInformation.getBtc_id());
            result.setU_id(commentInformation.getU_id());
            result.setU_id0(commentInformation.getU_id0());
            result.setBtc_com(commentInformation.getBtc_com());
            sSetComment.setComment(commentInformation.getMain_comment(), result);
        }else{
            result.setCode(0);
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);
    }

    @RequestMapping("/setGComment")
    public JSONObject setGComment(@RequestHeader("sKey") String sKey,@RequestBody String commentPostJson/**postJson是json字符串**/) {
        JSONObject jsonObject = JSONObject.parseObject(commentPostJson);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        JsonValue jsonValueClass = JSONObject.toJavaObject(jsonObject1, JsonValue.class);

        Result result = new Result();

        if(!DESUtils.checkSKey(MD5.Md5Lower32(commentPostJson),sKey)){
            result.setErrorMessage("请检查sKey");
            result.setCode(0);
        }else {
            if(jsonValueClass.getU_id()!=null&&jsonValueClass.getU_id().length()!=0){
                if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id()))){
                    result.setErrorMessage("请检查u_id");
                }
            }
            if(examine.exUId(Integer.parseInt(jsonValueClass.getU_id0()))){
                result.setErrorMessage("请检查u_id0");
            }
            if(examine.exGId(Integer.parseInt(jsonValueClass.getG_id()))){
                result.setErrorMessage("请检查g_id");
            }
            if(jsonValueClass.getGc_com()==null||jsonValueClass.getGc_com().length()==0){
                result.setErrorMessage("请检查gc_com");
            }
        }


        if(result.getErrorMessage().equals("success")){
            result.setErrorMessage("success");
            result.setGc_com(jsonValueClass.getGc_com());
            result.setU_id(jsonValueClass.getU_id());
            result.setU_id0(jsonValueClass.getU_id0());
            result.setG_id(jsonValueClass.getG_id());
            result.setGc_id(jsonValueClass.getGc_id());
            sSetComment.setGComment(jsonValueClass.isMain_comment(),result);
        }else{
            result.setCode(0);
        }

        Information information = new Information();
        information.setData(Tool.classToJson(result));

        return Tool.classToJson(information);

    }

}
