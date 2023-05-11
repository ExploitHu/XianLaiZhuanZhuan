package biao.community.service;

import biao.community.dao.DDeletePost;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SDeletePost {

    @Autowired
    DDeletePost dDeletePost;

    public void bSDeletePost(String bt_id){

        //删除帖子收藏
        dDeletePost.bDeleteCollect(bt_id);
        //删除帖子历史
        dDeletePost.bDeleteHistory(bt_id);
        //删除帖子评论
        dDeletePost.bDeleteComment(bt_id);
        //删除帖子点赞
        dDeletePost.bDeleteLike(bt_id);
        //删除帖子本身
        dDeletePost.bDeletePost(bt_id);
    }

    public void gSDeletePost(String g_id){

        //删除帖子收藏
        dDeletePost.gDeleteCollect(g_id);
        //删除帖子历史
        dDeletePost.gDeleteHistory(g_id);
        //删除帖子评论
        dDeletePost.gDeleteComment(g_id);
        //删除帖子点赞
        dDeletePost.gDeleteLike(g_id);
        //删除帖子本身
        dDeletePost.gDeletePost(g_id);
    }


}
