package biao.community.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DDeletePost {
    //删除帖子收藏
    void bDeleteCollect(String bt_id);
    //删除帖子历史
    void bDeleteHistory(String bt_id);
    //删除帖子评论
    void bDeleteComment(String bt_id);
    //删除帖子点赞
    void bDeleteLike(String bt_id);
    //删除帖子本身
    void bDeletePost(String bt_id);

    //删除帖子收藏
    void gDeleteCollect(String g_id);
    //删除帖子历史
    void gDeleteHistory(String g_id);
    //删除帖子评论
    void gDeleteComment(String g_id);
    //删除帖子点赞
    void gDeleteLike(String g_id);
    //删除帖子本身
    void gDeletePost(String g_id);

}
