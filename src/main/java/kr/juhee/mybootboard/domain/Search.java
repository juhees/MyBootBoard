package kr.juhee.mybootboard.domain;

import lombok.Data;

@Data
public class Search {
	
	private String searchCondition;
	private String searchKeyword;
	private String searchCategory;
	private int page; //페이징
}
