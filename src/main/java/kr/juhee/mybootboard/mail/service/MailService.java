package kr.juhee.mybootboard.mail.service;

import kr.juhee.mybootboard.mail.domain.MailDTO;

public interface MailService {

	public abstract void sendAttachMail(MailDTO mailDTO);

}