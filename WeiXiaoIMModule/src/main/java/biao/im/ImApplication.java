package biao.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImApplication {


    public static void main(String[] args) {
        SpringApplication.run(ImApplication.class, args);


        //输出版本、启动信息
        System.out.println("----------------------------------------");
        System.out.println("|                                      |");
        System.out.println("|          Websocket start             |");
        System.out.println("|                                      |");
        System.out.println("|     Date changed:2022-08-14          |");
        System.out.println("|                                      |");
        System.out.println("----------------------------------------");



    }

}
