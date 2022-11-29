package kr.juhee.mybootboard.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.board.entity.QBoard;
import kr.juhee.mybootboard.board.repository.BoardRepository;
import kr.juhee.mybootboard.board.service.BoardService;
import kr.juhee.mybootboard.domain.Search;

@Service
public class BoardServiceImpl implements BoardService {

	//BoardServiceImpl 클래스는 BoardRepository를 이용해 실질적인 db 연동을 처리
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());		
	}

	@Override
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
	}

	@Override
	public Page<Board> getBoardList(Search search) {		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(search.getSearchCondition().equals("TITLE")) {
		      builder.and(qboard.title.like("%" + search.getSearchKeyword() + "%"));
		      builder.and(qboard.category.like("%" + search.getSearchKeyword() + "%")); //카테고리별
		      
		} else if(search.getSearchCondition().equals("CONTENT")) {
		      builder.and(qboard.content.like("%" + search.getSearchKeyword() + "%"));
		      builder.and(qboard.category.like("%" + search.getSearchKeyword() + "%"));
		}		
		
		Pageable pageable = PageRequest.of(search.getPage(), 10, Sort.Direction.DESC, "seq");
		
		return boardRepo.findAll(builder, pageable);
	}

	@Override
	public List<Board> getExcelList() {
		
		return (List<Board>) boardRepo.findAll();
	}
	
	
	

}










