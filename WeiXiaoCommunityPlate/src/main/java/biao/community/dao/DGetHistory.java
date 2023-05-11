package biao.community.dao;

import biao.community.information.port2_10and3_9.BHistory;
import biao.community.information.port2_10and3_9.GHistory;
import org.apache.ibatis.annotations.Mapper;
import biao.community.information.port2_10and3_9.JsonValue;
import java.util.List;

@Mapper
public interface DGetHistory {

    //获取社区帖子浏览记录
    List<BHistory> bGetHistory(JsonValue jsonValueClass);

    //获取社区帖子浏览记录
    List<GHistory> gGetHistory(JsonValue jsonValueClass);

}
