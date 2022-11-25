package kr.juhee.mybootboard.board.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import kr.juhee.mybootboard.member.entity.Member;

public class SecurityUser extends User {
	
	private Member member;
	
	public SecurityUser(Member member) {
		super(member.getId(), member.getPassword(),
		AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member=member;
	}
	
	public Member getMember() {
		return member;
	}

}
