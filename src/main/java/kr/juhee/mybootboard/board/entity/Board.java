package kr.juhee.mybootboard.board.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.juhee.mybootboard.member.entity.Member;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "member") //Member와의 순환 참조를 해결하기 위해 member 변수 제외
public class Board {
	
	public Board() {
		
	}
	
	public Board(Member member, String title, String content) {
		
		this.member = member;
		this.title = title;
		this.content= content;
	}
	
	@Id
	@GeneratedValue
	private Long seq;
	
	private String title;
	private String content;
	private String category;
	
	@Temporal(TemporalType.TIMESTAMP) //날짜 타입을 매핑할 때 사용
	@Column(insertable=false, updatable=false, columnDefinition = "date default sysdate") 
	private Date createDate = new Date();
	//insertable : SQL INSERT문에 해당 컬럼을 포함할지 여부, updatable : SQL UPDATE문에 해당 컬럼을 포함할지 여부
	//날짜기본형식 time, day, month, year 형태저장
	
	@Column(updatable=false)
	private Long cnt = 0L;
	
	@ManyToOne //다대일관계 매핑
	@JoinColumn(name="MEMBER_ID", nullable = false, updatable = false) 
	private Member member; //Member 타입의 member변수 추가
	//@JoinColumn은 MEMBER_ID칼럼을 통해 외래키를 관리하게 하기위함
	//nullable은 즉시 로딩을 할 때 외부조인이 아닌 내부조인으로 처리해 성능을 향상시키기 위함
	
	public void setMember(Member member) {
		this.member=member;
		member.getBoardList().add(this);
		
	}
	
	
}
