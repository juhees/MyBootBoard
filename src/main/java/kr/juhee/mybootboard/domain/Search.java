package kr.juhee.mybootboard.domain;

import lombok.Data;

@Data
public class Search {
	
	private String searchCondition;
	private String searchKeyword;
	private int page;
}
