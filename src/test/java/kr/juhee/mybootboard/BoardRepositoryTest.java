package kr.juhee.mybootboard;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.board.repository.BoardRepository;
import kr.juhee.mybootboard.domain.Role;
import kr.juhee.mybootboard.member.entity.Member;
import kr.juhee.mybootboard.member.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class BoardRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	//데이터 등록 기능 메소드
	@Test
	public void testInsert() {
		
		Member member1 = new Member(
				"member", encoder.encode("member123"), "둘리", Role.ROLE_MEMBER, "y"
		);
		memberRepo.save(member1);
		
		Member member2 = new Member(
				"admin", encoder.encode("admin123"), "도우너", Role.ROLE_ADMIN, "y"
		);
		memberRepo.save(member2);
		
		for(int i = 1; i<=3; i++) {
			Board board
			= new Board(member1, member1.getName()+"가 등록한 게시글"+i, member1.getName()+"가 등록한 게시글"+i);
			boardRepo.save(board);
		}//for
		
		for(int i = 1; i<=3; i++) {
			Board board
			= new Board(member2, member2.getName()+"가 등록한 게시글"+i, member2.getName()+"가 등록한 게시글"+i);
			boardRepo.save(board);
		}//for
		
	}
	
	//조회한 게시글의 상세 정보 출력 메소드
	//@Test
	public void testGetBoard() {
		
		Board board = boardRepo.findById(1L).get();
		
		log.info("[ "+board.getSeq()+"번 게시 글 상세 정보 ]");
		log.info("제목\t : " +board.getTitle());
		log.info("작성자\t : " +board.getMember().getName());
		log.info("내용\t : " +board.getContent());
		log.info("등록일\t : " +board.getCreateDate());
		log.info("조회수\t : " +board.getCnt());
	}
	
	
	//회원이 등록한 게시 글 목록 출력 메소드
	//@Test
	public void testGetBoardList() {
		
		Member member = memberRepo.findById("member").get();
		
		log.info("[ "+member.getName()+"가 등록한 게시글 ]");
		for(Board board : member.getBoardList()) {
			log.info("--->"+board.toString());
		}
	}
	
	

}//class


