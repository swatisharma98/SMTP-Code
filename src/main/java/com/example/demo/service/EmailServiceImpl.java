package com.example.demo.service;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.demo.pojo.EmailDetails;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;


@Service
public class EmailServiceImpl implements EmailService{
	
	
	
	@Value("${spring.mail.username}") private String sender;
	
	 @Autowired
	 private JavaMailSender javaMailSender;
	 
	 
	 @Autowired
	 private Configuration configuration;
	 
	 
	 @Override
		public String sendSimpleMail(Map<String, Object> model) {
		    
	        SimpleMailMessage mailMessage
	            = new SimpleMailMessage();

	        mailMessage.setFrom(sender);
	        mailMessage.setTo("rudreshbansal3@gmail.com");
	        mailMessage.setText("Learn How to send Email using Spring Boot!!!\n\nThanks,\nSwati");
	        mailMessage.setSubject("Spring Boot - Email Example");
	        
	        javaMailSender.send(mailMessage);
	        return "Mail Sent Successfully...";
	        
		}


	@Override
	public String sendMail(EmailDetails emailDetails,Map<String,Object> model)  {
	 
			model.put("name", "XXX !!");
	
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
			try {
				helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
				helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
				Template template =  configuration.getTemplate("email-template.ftl");
				String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				helper.setTo(emailDetails.getRecipient());
				helper.setText(html,true);
		        helper.setSubject(emailDetails.getSubject());
		        helper.setFrom(sender);
		        // Sending the mail
		        javaMailSender.send(message);
		        return "Mail Sent Successfully...";
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedTemplateNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
			 return "Mail Not Sent...";

	}

	
}
