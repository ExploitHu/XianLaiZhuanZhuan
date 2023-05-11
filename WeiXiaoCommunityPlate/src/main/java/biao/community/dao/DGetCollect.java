package biao.community.dao;

import biao.community.information.port2_11and3_10.BCollect;
import biao.community.information.port2_11and3_10.GCollect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DGetCollect {

    //获取用户收藏（社区）
    List<BCollect> bgetCollect(int u_id);

    //获取用户收藏（交易）
    List<GCollect> ggetCollect(int u_id);
}
