package com.example.shop.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.example.shop.dto.MailDTO;
import com.example.shop.entity.UserEntity;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private SpringTemplateEngine templateEngine;

	public void sendEmail(MailDTO mailDTO, String templateName) {
		try {

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

			Context context = new Context();
			context.setVariable("name", mailDTO.getTo());
			context.setVariable("content", mailDTO.getContent());
			String html = templateEngine.process("registermail", context);

			/// send email
			helper.setTo(mailDTO.getTo());
			helper.setSubject(mailDTO.getSubject());
			helper.setFrom("fostter2@gmail.com");
			helper.setText(html, true);
			javaMailSender.send(message);

		} catch (MessagingException e) {
//		    logger.error("Email sent with error: " + e.getMessage());
		}
	}
}
