package com.example.demo.service;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import com.example.demo.pojo.EmailDetails;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public interface EmailService {
	
	String sendSimpleMail(Map<String, Object> model);

	String sendMail(EmailDetails emailDetails, Map<String,Object> model);

	
}
