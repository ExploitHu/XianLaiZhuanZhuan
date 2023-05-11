package biao.community;

import biao.community.dao.Examine;
import biao.community.time.GetTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableWebMvc
public class CommunityApplication {


    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);

        //输出版本、启动信息
        System.out.println("----------------------------------------");
        System.out.println("|                                      |");
        System.out.println("|   WeiXiao Community Services start   |");
        System.out.println("|                                      |");
        System.out.println("|     Date changed:2022-05—31          |");
        System.out.println("|                                      |");
        System.out.println("----------------------------------------");



    }

}
