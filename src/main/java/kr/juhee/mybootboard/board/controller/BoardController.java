package kr.juhee.mybootboard.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.board.security.SecurityUser;
import kr.juhee.mybootboard.board.service.BoardService;
import kr.juhee.mybootboard.domain.Search;


@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	//BoardService 컴포넌트를 의존성 주입
	
	//getBoardList.html 페이지에서 검색 결과를 사용할 수 있도록 Model에 검색한 글 목록 보여줌
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model, Search search) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		Page<Board> boardList = boardService.getBoardList(search);
		model.addAttribute("boardList", boardList);
		return "/board/getBoardList";
	}
	
	//제목 클릭시 게시 글 상세 화면 제공 메소드
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "/board/getBoard";
	}
	
	//글 등록 화면으로 전환해주는 메소드
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "/board/insertBoard";
	}
	
	//실질적으로 글 등록 처리를 담당하는 메소드
	@PostMapping("/insertBoard")
	public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	//글 수정 기능을 담당하는 메소드
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}
	
	//글 삭제를 담당하는 메소드
	@GetMapping("deleteBoard")
	public String deleteBoardView(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
	

}//class

