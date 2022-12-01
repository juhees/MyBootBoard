package kr.juhee.mybootboard.mail.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class MailDTO implements Serializable {
	
	public static final long serialVersionUID = 9823477983284L;
	
	public MailDTO() {
	}
	
	public MailDTO(String from, String to,
			String subject, String mcontent, String[] filePath) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.mcontent = mcontent; //board 엔티티와 이름 겹쳐서 바꿔줌
		this.filePath = filePath;
	}
	
	private String from;		// 발신자 이메일
	private String to;			// 수신자 이메일
	private String subject;		// 메일 제목
	private String mcontent;	// 메일 내용
	private String[] filePath;	// 첨부파일

} // class





