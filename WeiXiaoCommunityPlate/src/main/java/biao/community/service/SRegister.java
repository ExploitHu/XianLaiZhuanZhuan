package biao.community.service;

import biao.community.dao.DRegister;
import biao.community.information.port5_1.RegisterMessage;
import biao.community.time.GetTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SRegister {

    @Autowired
    DRegister dRegister;

    /**
     * 记录被占用id
     * String  邮箱
     * Integer 待分配的id
     */
    public String establishUserMessage(RegisterMessage registerMessage) {
        //判断此必传属性是否正确

        //申请一个新的id
        int min = registerMessage.getU_city() * 100000;
        int max = (registerMessage.getU_city() + 1) * 100000 - 1;
        int id = 0;

        //SRegister.occupyId.put("468709019@qq.com",10000000);
        synchronized (this){
            id = dRegister.minimumIntervalVacancyValue(min, max);

            //判断id是否为0（溢出）
            if(id == 0)
            {
                return "该校用户数量达到上限请联系管理员";
            }

            //查询校区中文名
            String cityName = dRegister.getCampus(registerMessage.getU_city());

            //注册日期
            String nowTime = GetTime.getWebsiteDatetime("http://www.ntsc.ac.cn");



            if(registerMessage.getU_sex().length() == 0){
                registerMessage.setU_sex("保密");
            }


            //写入user表数据
            dRegister.establishUserMessage(
                    id,
                    registerMessage.getU_email(),
                    registerMessage.getU_pass(),
                    cityName,
                    registerMessage.getU_birth(),
                    registerMessage.getU_sex(),
                    registerMessage.getU_nickname(),
                    registerMessage.getU_tel(),
                    registerMessage.getU_name(),
                    registerMessage.getU_num(),
                    nowTime,
                    registerMessage.getU_head()
            );
        }


        //填写user_anon表
        dRegister.establishAnonUserMessage(id,registerMessage.getU_sex());


        return Integer.toString(id);

    }
}
