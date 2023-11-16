package com.llx;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MailSender {
    private String smtp;
    private String username;
    private String password;
    private Session session;
    public MailSender(String _smtp, String _username, String _password){
        smtp = _smtp;
        username = _username;
        password = _password;

        Properties props = new Properties();
        props.put("mail.smtp.host", smtp); // SMTP主机名
        props.put("mail.smtp.port", "25"); // 主机端口号
        props.put("mail.smtp.auth", "true"); // 是否需要用户认证
        props.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密
        // 获取Session实例:
        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void send(String receiver, String subject, String text) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        // 设置发送方地址:
        message.setFrom(new InternetAddress(username));
        // 设置接收方地址:
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        // 设置邮件主题:
        message.setSubject(subject, "UTF-8");
        // 设置邮件正文:
        message.setText(text, "UTF-8");
        // 发送:
        Transport.send(message);
    }
}
