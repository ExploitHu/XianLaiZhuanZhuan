package biao.community.dao;

import org.apache.ibatis.annotations.Mapper;
import biao.community.information.port5_4.CampusInformation;

import java.util.List;

@Mapper
public interface DGetCampusInformation {

    List<CampusInformation> getInformation();

}
