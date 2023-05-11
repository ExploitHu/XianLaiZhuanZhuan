package biao.community.dao;

import biao.community.TestStruct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DTest {
    void selectB(int id,int sum);

    void setToBeRead(int u_id,int b,int g);
}
