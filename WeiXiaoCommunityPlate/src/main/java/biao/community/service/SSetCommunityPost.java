package biao.community.service;

import biao.community.dao.DSetCommunityPost;
import biao.community.information.port2_6.CommunityPostInformation;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SSetCommunityPost {

    @Autowired
    DSetCommunityPost dSetCommunityPost;

    public String setCommunityPost(CommunityPostInformation information){

        //information.setBt_image("aa");
        dSetCommunityPost.setCommunityPost(information);

        return Integer.toString(information.getBt_id());
    }
}
