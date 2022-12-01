package kr.juhee.mybootboard.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.juhee.mybootboard.mail.component.GoogleSMTPMailSender;
import kr.juhee.mybootboard.mail.domain.MailDTO;
import kr.juhee.mybootboard.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private GoogleSMTPMailSender mailSender;

	@Override
	public void sendAttachMail(MailDTO mailDTO) {
		try {
			mailSender.sendMessageWithAttachment(mailDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

} // class




