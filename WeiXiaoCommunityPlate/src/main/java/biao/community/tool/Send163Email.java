package biao.community.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class Send163Email {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;


    public String send(String email) throws Exception {

        Context context = new Context();

        //邮件主题
        String subject = "微校注册验证码";

        //发送时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Map<String, Object> dataMap = new HashMap<>();
        //接收人
        dataMap.put("email", email);
        //验证码  sum=6  6位验证码
        String verificationCode = Tool.randomNumberAlphabet(6);
        dataMap.put("code", verificationCode);
        //发送时间
        dataMap.put("createTime", sdf.format(new Date()));

        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }

        //html模板
        String templateContent = templateEngine.process("registerTemplate", context);
        //邮件
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(templateContent, true);
        javaMailSender.send(message);

        //发送成功提示信息
        System.out.println("\033[32;1m" + "发送给 " + email + " 的邮件发送成功" + "\033[0m");

        return verificationCode;
    }
}
