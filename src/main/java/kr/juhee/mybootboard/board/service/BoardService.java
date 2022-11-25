package kr.juhee.mybootboard.board.service;

import java.util.List;

import org.springframework.data.domain.Page;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.domain.Search;

public interface BoardService {
	
	void insertBoard(Board board);
	
	void updateBoard(Board board);
	
	void deleteBoard(Board board);
	
	Board getBoard(Board board);
	
	Page<Board> getBoardList(Search search);
	
	//엑셀
	List<Board> getExcelList();
	
	

}
