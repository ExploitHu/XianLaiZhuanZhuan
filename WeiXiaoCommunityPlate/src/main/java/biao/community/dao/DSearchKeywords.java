package biao.community.dao;


import biao.community.information.port2_1and3_1.CommunityPostInformation;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import biao.community.information.port2_7.JsonValue;

@Mapper
public interface DSearchKeywords {

    //(多个字)搜索关键词，获取size个帖子
    List<CommunityPostInformation> searchKeywordsGetSize(JsonValue jsonValueClass);

    //(单个字)搜索关键词，获取size个帖子
    //List<CommunityPostInformation> searchKeywordGetSize(JsonValue jsonValueClass);
}
