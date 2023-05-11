package biao.im.tool;

import biao.im.controller.WsServerEndpoint;
import biao.im.message.MessageUtils;
import biao.im.message.ResultMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


@Component
public class DelayTask {

    @Async
    public void test() {
        try {
            Thread.sleep(5000);
            System.out.println("执行延时任务");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 检查消息是否送达，没有送达重新发送
     *
     * @param resultMessage    本次发送的消息
     * @param wsServerEndpoint 发送人
     * @param sum              剩余重发次数为（sum - 1）
     */

    public void toDetectTheRetransmission(ResultMessage resultMessage, WsServerEndpoint wsServerEndpoint, int sum) {

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000 * 10);
                //判断消息是否送达
                //没有送达
                if (WsServerEndpoint.md5.contains(resultMessage.getMd5()) == true) {
                    //多次重发失败，记录在日志里
                    if (sum == 0) {
                        System.out.println("--------------------------------------------");
                        System.out.println(resultMessage.getFromName() + "有一条消息发送失败");
                        System.out.println("--------------------------------------------");

                    } else {
                        System.out.println("--------------------------------------------");
                        System.out.println("重发消息");
                        System.out.println("--------------------------------------------");
                        //重发消息
                        wsServerEndpoint.getSession().getBasicRemote().sendText(MessageUtils.getMessage(resultMessage));
                        //建立设置一个新的检查任务
                        toDetectTheRetransmission(resultMessage, wsServerEndpoint, sum - 1);
                    }
                } else {
                    System.out.println("--------------------------------------------");
                    System.out.println("验证通过，消息发送成功");
                    System.out.println("--------------------------------------------");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });


    }
}