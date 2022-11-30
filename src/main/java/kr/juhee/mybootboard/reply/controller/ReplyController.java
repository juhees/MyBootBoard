package kr.juhee.mybootboard.reply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.board.security.SecurityUser;
import kr.juhee.mybootboard.member.entity.Member;
import kr.juhee.mybootboard.reply.entity.Reply;
import kr.juhee.mybootboard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/")
public class ReplyController {
	
	@Autowired
	private final ReplyService replyService;
	
	@PostMapping("/replywrite")
	public String replyWrite(@ModelAttribute Reply reply, Board board, @AuthenticationPrincipal SecurityUser principal) {
		
		Member member = principal.getMember();
		replyService.replyWriter(reply, member, board);
		
		return "redirect:/board/getBoard?seq=" + board.getSeq();
	}
	
	@GetMapping("/replyDelete")
	public String replyDelete(Reply reply, Board board) {
		replyService.replyDelete(reply);
		return "redirect:/board/getBoard?seq="+board.getSeq();
	}
	
	@GetMapping("/replyUpdate")
	public String replyUpdate(Reply reply, Board board) {
		replyService.replyUpdate(reply);
		return "redirect:/board/getBoard?seq="+board.getSeq();
	}

}
