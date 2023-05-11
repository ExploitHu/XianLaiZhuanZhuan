package biao.community.dao;

import biao.community.information.port2_8and3_7.BClassify;
import biao.community.information.port2_8and3_7.GClassify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DGetClassify {

    //获取社区分区列表
    List<BClassify> getBClassify();


    //获取二手交易分区列表
    List<GClassify> getGClassify();
}
