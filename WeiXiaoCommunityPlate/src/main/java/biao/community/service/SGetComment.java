package biao.community.service;

import biao.community.information.port2_17and3_16.BTobeComment;
import biao.community.information.port2_17and3_16.GTobeComment;
import biao.community.information.port2_3.GMainComment;
import biao.community.information.port2_3.GSonComment;
import biao.community.tool.Tool;
import biao.community.dao.DGetComment;
import biao.community.information.port2_3.BMainComment;
import biao.community.information.port2_3.BSonComment;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SGetComment {

    @Autowired
    DGetComment dGetComment;

    public List<JSONObject> getBComment(int bt_id) {

        List<BMainComment> listMain = dGetComment.getBMainComment(bt_id);
        List<JSONObject> mainJsonObject = new ArrayList<JSONObject>();

        for (int i = 0; i < listMain.size(); i++) {
            List<BSonComment> listSon = dGetComment.getBSonComment(listMain.get(i).getBtc_id(),Integer.toString(bt_id));

            //设置主评论格式
            listMain.get(i).setBtc_time(listMain.get(i).getBtc_time().substring(5,10));


            List<JSONObject> sonJsonObject = new ArrayList<JSONObject>();
            for (int j = 0; j < listSon.size(); j++) {

                //设置子评论格式
                listSon.get(j).setBtc_time(listSon.get(j).getBtc_time().substring(5,10));

                sonJsonObject.add(Tool.classToJson(listSon.get(j)));
            }
            listMain.get(i).setSonComment(sonJsonObject);

            mainJsonObject.add(Tool.classToJson(listMain.get(i)));
        }


        return mainJsonObject;
    }

    public List<JSONObject> getGComment(int g_id) {

        List<GMainComment> listMain = dGetComment.getGMainComment(g_id);
        List<JSONObject> mainJsonObject = new ArrayList<JSONObject>();

        for (int i = 0; i < listMain.size(); i++) {
            List<GSonComment> listSon = dGetComment.getGSonComment(listMain.get(i).getGc_id(),Integer.toString(g_id));


            //设置主评论格式
            listMain.get(i).setGc_time(listMain.get(i).getGc_time().substring(5,10));

            List<JSONObject> sonJsonObject = new ArrayList<JSONObject>();
            for (int j = 0; j < listSon.size(); j++) {

                //设置子评论格式
                listSon.get(j).setGc_time(listSon.get(j).getGc_time().substring(5,10));

                sonJsonObject.add(Tool.classToJson(listSon.get(j)));
            }
            listMain.get(i).setSonComment(sonJsonObject);

            mainJsonObject.add(Tool.classToJson(listMain.get(i)));
        }


        return mainJsonObject;
    }

    public void getBToBeRead(String u_id,String slm,biao.community.information.port2_17and3_16.Result result){
        List<BTobeComment> resultList ;

        int l = 0;
        if(slm.equals("0")){
            l = dGetComment.getBTobeCommentNumber(Integer.parseInt(u_id));
        }
        resultList = dGetComment.getBToBeRead(u_id,slm,0);



        dGetComment.resetBTobeComment(u_id);
        List<JSONObject> resultJsonList  = new ArrayList<JSONObject>();
        for (BTobeComment bTobeComment:resultList){
            List<String> listImage = Arrays.asList(bTobeComment.getBt_image().split(","));
            if(listImage.size() != 0){
                bTobeComment.setBt_image(listImage.get(0));
            }else {
                bTobeComment.setBt_image(null);
            }

            JSONObject jsonObject = Tool.classToJson(bTobeComment);
            jsonObject.remove("id");

            resultJsonList.add(jsonObject);
        }
        if(resultList.size()!=0){
            result.setSlm(resultList.get(0).getId());
        }else {
            result.setState(null);
        }

        result.setCommentList(resultJsonList);

    }

    public void getGToBeRead(String u_id,String slm,biao.community.information.port2_17and3_16.Result result){
        List<GTobeComment> resultList ;

        int l = 0;
        if(slm.equals("0")){
            l = dGetComment.getGTobeCommentNumber(Integer.parseInt(u_id));
        }

        resultList = dGetComment.getGToBeRead(u_id,slm,l);



        dGetComment.resetGTobeComment(u_id);
        List<JSONObject> resultJsonList  = new ArrayList<JSONObject>();
        for (GTobeComment gTobeComment:resultList){
            List<String> listImage = Arrays.asList(gTobeComment.getG_image().split(","));
            if(listImage.size() != 0){
                gTobeComment.setG_image(listImage.get(0));
            }else {
                gTobeComment.setGc_time(null);
            }

            JSONObject jsonObject = Tool.classToJson(gTobeComment);
            jsonObject.remove("id");

            resultJsonList.add(jsonObject);
        }
        if(resultList.size()!=0){
            result.setSlm(resultList.get(0).getId());
        }else {
            result.setState(null);
        }

        result.setCommentList(resultJsonList);
    }
}
