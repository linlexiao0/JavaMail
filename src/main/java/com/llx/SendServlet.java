package com.llx;

import com.alibaba.fastjson2.JSONObject;
import jakarta.mail.MessagingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Hello Servlet
 *
 * @author kendoziyu
 * @since 2020/10/22 0022
 */
//@WebServlet(urlPatterns = "/send")
public class SendServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
        JSONObject data = HttpGetJSON.getJSON(request);
        JSONObject res = new JSONObject();
        do{
            if(data==null){
                res.put("code", 4001);
                res.put("msg","JSON解析失败！");
                break;
            }
            String mail_host = data.getString("mail_host");
            String mail_user = data.getString("mail_user");
            String mail_pass = data.getString("mail_pass");

//            String sender_email = data.getString("sender_email");
//            String sender_nickname = data.getString("sender_nickname");
            String receivers = data.getString("receivers");
            String subject = data.getString("subject");
            String msg = data.getString("msg");


            if(mail_host==null || mail_user==null || mail_pass==null
                    || receivers==null || subject==null || msg==null){
                res.put("code", 4002);
                res.put("msg","参数提供不全！");
                break;
            }

            // TODO 空串也要报错

            MailSender sender = new MailSender(mail_host, mail_user, mail_pass);
            try{
                sender.send(receivers, subject, msg);
                res.put("code", 200);
                res.put("msg","发送成功！");
            } catch (MessagingException e) {
                res.put("code", 4003);
                res.put("msg","发送失败！");
            }
        } while(false);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().write(String.valueOf(res));
    }
}

