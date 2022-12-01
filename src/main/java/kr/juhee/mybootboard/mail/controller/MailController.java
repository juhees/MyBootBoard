package kr.juhee.mybootboard.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.board.service.BoardService;
import kr.juhee.mybootboard.mail.domain.MailDTO;
import kr.juhee.mybootboard.mail.service.MailService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MailController {
	
    private final MailService mailService;
    
    @Autowired
	private BoardService boardService;

    @GetMapping("/mail")
    public String dispMail() {
        return "getBoard";
    }

    @PostMapping("/mail")
    public String execMail(Board board, MailDTO mailDto, Model model ) {
		model.addAttribute("board",boardService.getBoard(board));
        mailService.sendAttachMail(mailDto);
        return "redirect:/board/getBoardList";
    }
}
