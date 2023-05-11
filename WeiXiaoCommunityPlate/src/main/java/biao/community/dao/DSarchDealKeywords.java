package biao.community.dao;

import biao.community.information.port3_6.DealInformation;
import org.apache.ibatis.annotations.Mapper;
import biao.community.information.port3_6.JsonValue;
import java.util.List;

@Mapper
public interface DSarchDealKeywords {

    //查询交易帖子关键词
    List<DealInformation> searchKeywordsGetSize(JsonValue jsonValue);

    //（单字）查询交易帖子关键词
    //List<DealInformation> searchKeywordGetSize(JsonValue jsonValue);

    boolean isGCollect(String g_id,String u_id);
}
