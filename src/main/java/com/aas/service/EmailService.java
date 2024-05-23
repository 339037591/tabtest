package com.aas.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.sender}")
    private String sender;
    @Value("${mail.open.test}")
    private Boolean isTest;
    @Value("${mail.test.user.email}")
    private String testUserEmail;

    /**
     * 发送普通邮件
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 内容
     */
    public void sendSimpleMailMessge(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        if(isTest){
            if(StringUtils.isNotBlank(testUserEmail)){
                message.setTo(testUserEmail.split(";"));
            }
        }else{
            message.setTo(to);
        }
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            log.error("发送简单邮件时发生异常!", e);
        }
    }
    /**
     * 发送普通邮件
     *
     * @param to      收件人String数组
     * @param subject 标题
     * @param content 内容
     */
    public void sendSimpleMailMessge(String[] to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        if(isTest){
            if(StringUtils.isNotBlank(testUserEmail)){
                message.setTo(testUserEmail.split(";"));
            }
        }else{
            message.setTo(to);
        }
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            log.error("发送普通邮件时发生异常!", e);
        }
    }
    /**
     * 发送 HTML 邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendMimeMessge(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            if(isTest){
                if(StringUtils.isNotBlank(testUserEmail)){
                    helper.setTo(testUserEmail.split(";"));
                }
            }else{
                helper.setTo(to);
            }
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("发送MimeMessge时发生异常！", e);
        }
    }
    /**
     * 发送 HTML 邮件
     *
     * @param to      收件人String数组
     * @param subject 主题
     * @param content 内容
     */
    public void sendMimeMessge(String[] to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            if(isTest){
                if(StringUtils.isNotBlank(testUserEmail)){
                    helper.setTo(testUserEmail.split(";"));
                }
            }else{
                helper.setTo(to);
            }
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("发送MimeMessge时发生异常！", e);
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件路径
     */
    public void sendMimeMessge(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            if(isTest){
                if(StringUtils.isNotBlank(testUserEmail)){
                    helper.setTo(testUserEmail.split(";"));
                }
            }else{
                helper.setTo(to);
            }
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(filePath);
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("发送带附件的MimeMessge时发生异常！", e);
        }
    }
    /**
     * 发送带附件的邮件
     *
     * @param to       收件人String数组
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件路径
     */
    public void sendMimeMessge(String[] to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            if(isTest){
                if(StringUtils.isNotBlank(testUserEmail)){
                    helper.setTo(testUserEmail.split(";"));
                }
            }else{
                helper.setTo(to);
            }
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(filePath);
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("发送带附件的MimeMessge时发生异常！", e);
        }
    }
}
