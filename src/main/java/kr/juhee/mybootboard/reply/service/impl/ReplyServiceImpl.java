package kr.juhee.mybootboard.reply.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.member.entity.Member;
import kr.juhee.mybootboard.reply.entity.Reply;
import kr.juhee.mybootboard.reply.repository.ReplyRepository;
import kr.juhee.mybootboard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private final ReplyRepository replyRepository;

	@Override
	public Reply replyWriter(Reply reply, Member member, Board board) {
		
		reply.setBoard(board);
		reply.setMember(member);
		
		return replyRepository.save(reply);
	}

	@Override
	public Page<Reply> getReplyList(Long boardId, int page) {
		
		Pageable pageable = PageRequest.of(page, 5, Sort.Direction.DESC, "reply_id");

		return replyRepository.selectReplyAllFind(boardId, pageable);
	}

	@Override
	public void replyDelete(Reply reply) {
		
		replyRepository.deleteById(reply.getReplyId());
	}

	@Override
	public void replyUpdate(Reply reply) {
		
		Reply findReply = replyRepository.findById(reply.getReplyId()).get();
		findReply.setContent(reply.getContent());
		
		replyRepository.save(findReply);
	}

	
}
