package biao.community.service;

import biao.community.information.port2_5.Result;
import biao.community.dao.DSetComment;
import biao.community.time.GetTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SSetComment {

    @Autowired
    DSetComment dSetComment;

    @Autowired
    SynchronizedByKey synchronizedByKey;

    public void setComment(boolean isMain_comment, Result result) {

        if (isMain_comment) {
            //上锁防止一个btc_id被重复获取
            synchronizedByKey.exec(result.getBt_id(),()->{
                //获取新的主评论id(btc_id)
                result.setBtc_id(Integer.toString(dSetComment.getMinGapvalue(Integer.parseInt(result.getBt_id()))));
                dSetComment.setMainComment(result);
                result.setU_id("");
                dSetComment.bReadAnd1(null,result.getBt_id(),result.getU_id0());
            });

        } else {
            //设置一条子评论
            dSetComment.setSonComment(result);
            dSetComment.bReadAnd1(result.getU_id(),null,result.getU_id0());
        }

        //获取评论时间
        result.setBtc_time(GetTime.antonymFormatTime(dSetComment.selectBtcTime(result.getBtc_time())));

    }


    public void setGComment(boolean isMain_comment,biao.community.information.port3_4.Result result){

        if(isMain_comment){
            synchronizedByKey.exec(result.getG_id(),()->{
                //获取新的主评论id(btc_id)
                result.setGc_id(Integer.toString(dSetComment.getGMinGapvalue(Integer.parseInt(result.getG_id()))));
                dSetComment.setGMainComment(result);
                result.setU_id("");
                //System.out.println("写一条主评论");
                //result.setBtc_id(String.valueOf(btc_id));
                dSetComment.gReadAnd1(null,result.getG_id(),result.getU_id0());
            });
        }else{
            dSetComment.setGSonComment(result);
            dSetComment.gReadAnd1(result.getU_id(), null,result.getU_id0());
        }

        //
        result.setGc_time(GetTime.antonymFormatTime(dSetComment.selectGcTime(result.getGc_time())));
    }
}
