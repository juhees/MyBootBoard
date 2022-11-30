package kr.juhee.mybootboard.member.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.domain.Role;
import kr.juhee.mybootboard.reply.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@ToString(exclude={"boardList","replyList"})
public class Member {
	
	public Member() {
		
	}
	
	public Member(String id, String password, String name, Role role, String enabled) {
		this.id = id;
		this.password= password;
		this.name = name;
		this.role = role;
		this.enabled = enabled;
	}
	
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	
	private String password;
	
	private String name;

	@Enumerated(EnumType.STRING) //회원의 권한 정보를 문자열로 저장하기 위함
	private Role role;
	
	private String enabled;
	
	@OneToMany(mappedBy = "member", fetch=FetchType.EAGER) 
	private List<Board> boardList = new ArrayList<Board>();
	//일대다 관계. Member가 양방향 매핑에서 연관과계의 주인이 아님을 설정하기 위해 mappedBy. 
	//Member가 조회될때 관련된 Board 목록도 조회되도록 fetch를 즉시 로딩(EAGER)로 설정.
	
	
	//reply
		@OneToMany(mappedBy = "member")
		private List<Reply> replyList = new ArrayList<Reply>();

}
