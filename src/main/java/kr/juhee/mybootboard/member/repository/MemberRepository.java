package kr.juhee.mybootboard.member.repository;

import org.springframework.data.repository.CrudRepository;

import kr.juhee.mybootboard.member.entity.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
