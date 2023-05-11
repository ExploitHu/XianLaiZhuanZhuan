package biao.community.tool;

import biao.community.controller.CSendVerificationCode;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


@Component
public class ExpiredVerificationCode {


    public static void timeToDelete(String Email, int time) {

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                //测试代码
                //System.out.println(CSendVerificationCode.verificationCode);
                //定时
                Thread.sleep(1000 * time);
                //定时结束后执行任务
                if (CSendVerificationCode.verificationCode.containsKey(Email)) {
                    CSendVerificationCode.verificationCode.get(Email).remove(0);
                    if (CSendVerificationCode.verificationCode.get(Email).size() == 0) {
                        CSendVerificationCode.verificationCode.remove(Email);
                    }
                    //测试代码
                    //System.out.println(CSendVerificationCode.verificationCode);
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });


    }
}