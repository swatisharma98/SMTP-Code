package com.example.demo.controller;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.EmailDetails;
import com.example.demo.service.EmailService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@RestController
public class EmailController {
	
	
	@Autowired private EmailService emailService;
	
	
	
	 @PostMapping("/sendMail")
	    public String
	    sendMail(Map<String,Object> model) 
	    {
	        String status
	            = emailService.sendSimpleMail(model);
	 
	        return status;
	    }

	
    @PostMapping("/sendSimpleMail")
    public String
    sendSimpleMail(@RequestBody EmailDetails emailDetails, Map<String,Object> model) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException
    {
        String status
            = emailService.sendMail(emailDetails,model);
 
        return status;
    }
    
   
}
