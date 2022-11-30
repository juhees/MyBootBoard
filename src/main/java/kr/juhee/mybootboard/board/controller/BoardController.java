package kr.juhee.mybootboard.board.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.board.security.SecurityUser;
import kr.juhee.mybootboard.board.service.BoardService;
import kr.juhee.mybootboard.domain.Search;
import kr.juhee.mybootboard.file.entity.FileEntity;
import kr.juhee.mybootboard.file.service.FileService;
import kr.juhee.mybootboard.reply.entity.Reply;
import kr.juhee.mybootboard.reply.service.ReplyService;


@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	//BoardService 컴포넌트를 의존성 주입
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private ReplyService replyService;
	
	
	//getBoardList.html 페이지에서 검색 결과를 사용할 수 있도록 Model에 검색한 글 목록 보여줌
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model, Search search) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");
		if(search.getSearchCategory()==null)
			search.setSearchCategory("");
		
		int currentPage=search.getPage();
		
		Page<Board> boardList = boardService.getBoardList(search, currentPage);
		
		if(boardList.getNumberOfElements()==0) {
			search.setPage(1);
		}else {
			search.setPage(boardList.getTotalPages());
		}
		
		model.addAttribute("boardList", boardList);
		int totalPage = boardList.getTotalPages(); 
		model.addAttribute("totalPage",totalPage);//전체 페이지 수
		model.addAttribute("search", search);
		return "/board/getBoardList";
	}
	
	//제목 클릭시 게시 글 상세 화면 제공 메소드
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model, Search search, @AuthenticationPrincipal SecurityUser principal) {
		
		int currentPage = search.getPage();
		List<FileEntity> files=fileService.fileAllView(board.getSeq());
		//reply
		Page<Reply> replyList = replyService.getReplyList(board.getSeq(),currentPage);
		
		if(replyList.getNumberOfElements() ==0) {
			search.setPage(1);
		}else {
			search.setPage(replyList.getTotalPages());
		}
		
		model.addAttribute("board", boardService.getBoard(board));
		model.addAttribute("all",files);
		//reply
		model.addAttribute("replyList", replyList);
		model.addAttribute("searchResult",search);
		
		return "/board/getBoard";
	}
	
	//글 등록 화면으로 전환해주는 메소드
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "/board/insertBoard";
	}
	
	//실질적으로 글 등록 처리를 담당하는 메소드
	//게시글 쓸 때 첨부파일
		@PostMapping("/insertBoard")
		public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal,
			@RequestParam("files") List<MultipartFile> files) throws Exception{
			board.setMember(principal.getMember());
			boardService.insertBoard(board);
			
			for(MultipartFile multipartFile : files) {
				board.setFileId(fileService.saveFile(multipartFile,board));
			}
			
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
	
	//이미지 뷰
	@GetMapping("/images/{fileId}")
	@ResponseBody
	public Resource imageView(@PathVariable("fileId")Long id, Model model)throws IOException{
		
		FileEntity file = fileService.downloadImage(id);
		String savePath = file.getSavedPath();
		
		if(savePath.substring(savePath.length()-3,savePath.length()).equals("png")
				|| savePath.substring(savePath.length()-3,savePath.length()).equals("jpg")) {
			UrlResource url = new UrlResource("file", savePath);
			
			model.addAttribute("test","exe");
			return url;
		}else {
			model.addAttribute("test","not");
		}
		return null;
	}
	
	//첨부파일 다운로드
	@GetMapping("/attach/{id}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException{
		
		FileEntity file = fileService.downloadImage(id);
		
		UrlResource resource = new UrlResource("file:" +file.getSavedPath());
		
		String encodedFileName = UriUtils.encode(file.getOrgName(), StandardCharsets.UTF_8);
		
		String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
	}
	
	
	

}//class

