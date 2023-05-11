package biao.community.service;

import biao.community.dao.DIsLike;
import biao.community.information.port7_1_4.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SIsLike {

    @Autowired
    DIsLike dIsLike;

    @Autowired
    SynchronizedByKey synchronizedByKey;

    public String addBIsLike(JsonValue jsonValue){

        synchronizedByKey.exec(jsonValue.getU_id(),()->{
            if(dIsLike.exBCRecord(jsonValue)){
                dIsLike.addBIsLike(jsonValue);
                dIsLike.addBCount(jsonValue);
            }
        });

        return  "success";
    }

    public void delBIsLike(JsonValue jsonValue){

        synchronizedByKey.exec(jsonValue.getU_id(),()->{
            if(!dIsLike.exBCRecord(jsonValue)){
                dIsLike.delBIsLike(jsonValue);
                dIsLike.reduceBCount(jsonValue);
            }
        });
    }


    public String addGIsLike(JsonValue jsonValue){

        synchronizedByKey.exec(jsonValue.getU_id(),()->{
            if(dIsLike.exGCRecord(jsonValue)){
                dIsLike.addGIsLike(jsonValue);
                dIsLike.addGCount(jsonValue);
            }
        });

        return "success";
    }

    public void delGIsLike(JsonValue jsonValue){

        synchronizedByKey.exec(jsonValue.getU_id(),()->{
            if(!dIsLike.exGCRecord(jsonValue)){
                dIsLike.delGIsLike(jsonValue);
                dIsLike.reduceGCount(jsonValue);
            }
        });
    }
}
