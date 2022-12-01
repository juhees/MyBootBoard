package kr.juhee.mybootboard.mail.component;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import kr.juhee.mybootboard.mail.domain.MailDTO;

@Component
public class GoogleSMTPMailSender {
	
	@Autowired
	private JavaMailSender javaEmailSender;
	
	private static final String FROM_ADDRESS = "juheenew@gmail.com";
	
	public void sendMessageWithAttachment(MailDTO mailDTO) throws Exception {

		MimeMessage message = javaEmailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(FROM_ADDRESS);
		helper.setTo(mailDTO.getTo());
		helper.setSubject(mailDTO.getSubject());
		helper.setText(mailDTO.getMcontent());
		
		for (String attachFileName : mailDTO.getFilePath()) {
			File file = new File(attachFileName);
			helper.addAttachment(file.getName()
				, new FileSystemResource(file));			
		}

		javaEmailSender.send(message);
	}
	
} // class
