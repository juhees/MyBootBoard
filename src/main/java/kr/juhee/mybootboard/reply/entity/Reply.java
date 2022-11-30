package kr.juhee.mybootboard.reply.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.member.entity.Member;
import lombok.Data;

@Data
@Entity(name="reply")
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long replyId;
	
	String content;
	
	@ManyToOne
	@JoinColumn(name="seq")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false)
	private Date createDate = new Date();
	
	public void setBoard(Board board) {
		this.board = board;
		board.getReply().add(this);
		
	}
	
	

}
