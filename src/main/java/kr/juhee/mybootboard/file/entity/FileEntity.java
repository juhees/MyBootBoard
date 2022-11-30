package kr.juhee.mybootboard.file.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kr.juhee.mybootboard.board.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Table(name="fileEntity")
@Entity
@ToString(exclude="board")
public class FileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	
	private String orgName;
	
	private String savedName;
	
	private String savedPath;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="seq")
	private Board board;
	
	public void setBoard(Board board) {
		this.board = board;
		board.getFileList().add(this);
	}

	@Builder
	public FileEntity(Long id, String orgName, String savedName, String savedPath) {
		this.id=id;
		this.orgName=orgName;
		this.savedName=savedName;
		this.savedPath=savedPath;
	}
	

}//class
