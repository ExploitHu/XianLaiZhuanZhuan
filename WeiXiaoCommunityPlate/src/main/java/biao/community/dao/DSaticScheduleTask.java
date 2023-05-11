package biao.community.dao;

import biao.community.timedTask.BInformation;
import biao.community.timedTask.GInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DSaticScheduleTask {
    //获取社区帖子信息
    List<BInformation> getBInformation(int sum);

    //写入社区帖子推荐值
    void setBRecommendedValue(List<BInformation> list);

    //获取社区帖子信息
    List<GInformation> getGInformation(int sum);

    //写入社区帖子推荐值
    void setGRecommendedValue(List<GInformation> list);


}
