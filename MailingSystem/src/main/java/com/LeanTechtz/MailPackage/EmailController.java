package com.LeanTechtz.MailPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.LeanTechtz.User.UserMail;

@Controller
public class EmailController {
	
	@Autowired
	Email email;
	
	@RequestMapping("/")
	public String display(){return "mail.html";}

	@RequestMapping("/send")
	public ModelAndView sendEmail(UserMail usermail) {
		ModelAndView model = new ModelAndView();
		model.setViewName("mail.html");
		
		//CREATE A MAIL SENDER
		JavaMailSenderImpl mailsender  = new JavaMailSenderImpl();
		mailsender.setHost(email.getHost());
		mailsender.setPort(email.getPort());
		mailsender.setUsername(email.getUsername());
		mailsender.setPassword(email.getPassword());
		
		//CREATE AN EMAIL INSTANCE
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(usermail.getEmail());
		mail.setTo("devop@gmail.com");
		mail.setSubject("New Email from: "+usermail.getName());
		mail.setText(usermail.getContent());
		
		//SEND
		mailsender.send(mail);
		return model;
		
	}
}
