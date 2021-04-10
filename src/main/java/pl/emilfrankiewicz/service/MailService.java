package pl.emilfrankiewicz.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	private JavaMailSender javaMailSender;

	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendSimpleEmail(String messageRecipient , String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(messageRecipient);
		message.setFrom("");

		message.setSubject(subject);
		message.setText(content);

		javaMailSender.send(message);
	}
}