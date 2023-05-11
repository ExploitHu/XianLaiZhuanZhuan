package biao.community.dao;

import biao.community.information.port7_1_4.JsonValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DIsLike {

    //检查社区帖子点赞记录是否存在
    boolean exBCRecord(JsonValue jsonValue);

    //增加一条社区帖子点赞记录
    void addBIsLike(JsonValue jsonValue);

    //增加社区帖子点赞数量
    void addBCount(JsonValue jsonValue);

    //减少社区帖子点赞数量
    void reduceBCount(JsonValue jsonValue);

    //删除一条社区帖子点赞记录
    void delBIsLike(JsonValue jsonValue);



    //检查交易帖子点赞记录是否存在
    boolean exGCRecord(JsonValue jsonValue);

    //增加一条交易帖子点赞记录
    void addGIsLike(JsonValue jsonValue);

    //增加交易帖子点赞数量
    void addGCount(JsonValue jsonValue);

    //减少交易帖子点赞数量
    void reduceGCount(JsonValue jsonValue);

    //删除一条交易帖子点赞记录
    void delGIsLike(JsonValue jsonValue);


}
