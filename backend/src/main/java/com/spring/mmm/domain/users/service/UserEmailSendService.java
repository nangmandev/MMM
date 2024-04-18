package com.spring.mmm.domain.users.service;

import com.spring.mmm.common.config.RedisDao;
import com.spring.mmm.common.exception.InternalServerCaughtException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserEmailSendService {

    private final JavaMailSender mailSender;
    private final RedisDao redisDao;


    private String makeRandomNumber() {
        StringBuilder authNumber = new StringBuilder();
        try {
            Random r = SecureRandom.getInstanceStrong();
            for(int i = 0; i < 6; i++) {
                authNumber.append(r.nextInt(10));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerCaughtException(e, this);
        }
        return authNumber.toString();
    }


    public String joinEmail(String email) {
        String authNumber = makeRandomNumber();
        String setFrom = "badacura@gmail.com";
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다.";
        String content =
                "'막내야 뭐 먹을래?'를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + authNumber + "입니다." +
                        "<br>" +
                        "인증번호를 입력해주세요";
        mailSend(setFrom, toMail, title, content, authNumber);
        return authNumber;
    }

    public void mailSend(String setFrom, String toMail, String title, String content, String authNumber) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new InternalServerCaughtException(e, this);
        }
        redisDao.setDataExpire(authNumber,toMail,60*5L);
    }

    public boolean checkAuthNum(String email,String authNum){
        return !(redisDao.getData(authNum) == null | !redisDao.getData(authNum).equals(email));
    }
}