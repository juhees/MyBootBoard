package kr.juhee.mybootboard.reply.service;

import org.springframework.data.domain.Page;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.member.entity.Member;
import kr.juhee.mybootboard.reply.entity.Reply;

public interface ReplyService {

   Reply replyWriter(Reply reply, Member member, Board board);
   
   Page<Reply> getReplyList(Long boardId, int page);
   
   void replyDelete(Reply reply);
   
   void replyUpdate(Reply reply);
   
}