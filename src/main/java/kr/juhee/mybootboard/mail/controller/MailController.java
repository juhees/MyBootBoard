package kr.juhee.mybootboard.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.board.service.BoardService;
import kr.juhee.mybootboard.domain.Search;
import kr.juhee.mybootboard.mail.dto.MailDto;
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
    public String execMail(MailDto mailDto, Search search, Model model ) {
    	if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		Page<Board> boardList = boardService.getBoardList(search);
		model.addAttribute("boardList", boardList);
        mailService.mailSend(mailDto);
        return "redirect:/board/getBoardList";
    }
}
