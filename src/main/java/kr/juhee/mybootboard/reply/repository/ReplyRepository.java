package kr.juhee.mybootboard.reply.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.juhee.mybootboard.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

	@Query(value = " SELECT * FROM reply WHERE seq = :boardId", nativeQuery = true)
	   Page<Reply> selectReplyAllFind(@Param("boardId") Long boardId, Pageable pageable);
}
