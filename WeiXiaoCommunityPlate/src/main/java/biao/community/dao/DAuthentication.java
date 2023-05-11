package biao.community.dao;

import biao.community.information.port5_6__9.AttcIfmt;
import biao.community.information.port5_6__9.CertificationStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DAuthentication {
    
    //写入认证信息
    void updAttcIfmt(AttcIfmt attcIfmt);

    //删除原有的
    void deleteOld(int u_id);

    //获取用户认证状态
    CertificationStatus getCertificationStatus(String u_id);

    //工作人员获取用户认证信息
    AttcIfmt getAttcIfmt(int num);

    //工作人员写入审核用户认证信息结果
    void auditAttcIfmt(String u_id,String auditStatus,String statements);

    //并且修改user表中认证信息字段
    void updateUIdentity(String u_id,String u_identity,String studentId);
}
