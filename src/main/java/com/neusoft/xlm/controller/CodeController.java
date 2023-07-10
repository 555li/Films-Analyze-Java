package com.neusoft.xlm.controller;

import com.neusoft.xlm.entity.CodeInfo;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RestController
public class CodeController {
    @PostMapping("/sendCode")
    public String sendCode(@RequestBody CodeInfo codeInfo) {
        String code = codeInfo.getCode();
        String email = codeInfo.getEmail();
        String fromEmail = "m13152797697@163.com"; // 发件人邮箱
        String password = "KZSKGQBBLWHDJKFP"; // SMTP服务器授权码
        String toEmail = email; // 收件人邮箱
        // 设置邮件服务器属性
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.smtp.auth", "true");
        // 连接SMTP服务器
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        try {
            // 创建邮件
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("验证码");
            message.setText("您的验证码是：" + code);
            // 发送邮件
            Transport.send(message);
            // 返回成功消息
            return "{\"msg\":\"验证码已发送\"}";
        } catch (MessagingException e) {
            e.printStackTrace();
            // 返回失败消息
            return "{\"msg\":\"发送验证码失败\"}";
        }
    }
}

