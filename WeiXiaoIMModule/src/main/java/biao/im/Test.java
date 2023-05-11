package biao.im;

import biao.im.dao.DStrandedMessage;
import biao.im.message.StrandedMessage;
import biao.im.tool.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test {

    @Autowired
    MailServiceImpl emailTool;

    @Autowired
    DStrandedMessage dStrandedMessage;


    @RequestMapping("/test")
    public String test() {

        List<StrandedMessage> list = dStrandedMessage.getStrandedMessage(11);

        StrandedMessage strandedMessage = new StrandedMessage();

        strandedMessage.setU_id1("55");
        strandedMessage.setU_id2("66");
        strandedMessage.setDia_message("测试消息");

        dStrandedMessage.setStrandedMessage(strandedMessage);


        //dStrandedMessage.deleteStrandedMessage(11);

        System.out.println(1);

        return "6654接口畅通";
    }

    @RequestMapping("/sendEmail")
    public String sendEmail() {


        emailTool.send("d", "as", "这是内容");

        return "发送邮件成功";
    }


}