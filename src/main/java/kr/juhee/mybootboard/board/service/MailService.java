package kr.juhee.mybootboard.board.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.board.entity.MailDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
	
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "juheenew@gmail.com";

    public void mailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        
        
        mailSender.send(message);
    }
}
