package biao.im.tool;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MailServiceImpl {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void send(String email, String subject, String text) {
        email = "468709019@qq.com";
        subject = "这是邮件主题";
        //创建邮件内容
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);//这里指的是发送者的账号
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        //发送邮件
        mailSender.send(message);
        System.out.println("\033[32;1m" + "发送给 " + email + " 的邮件发送成功" + "\033[0m");
    }
}
