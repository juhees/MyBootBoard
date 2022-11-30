package kr.juhee.mybootboard.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1640973745L;

    public static final QMember member = new QMember("member1");

    public final ListPath<kr.juhee.mybootboard.board.entity.Board, kr.juhee.mybootboard.board.entity.QBoard> boardList = this.<kr.juhee.mybootboard.board.entity.Board, kr.juhee.mybootboard.board.entity.QBoard>createList("boardList", kr.juhee.mybootboard.board.entity.Board.class, kr.juhee.mybootboard.board.entity.QBoard.class, PathInits.DIRECT2);

    public final StringPath enabled = createString("enabled");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final ListPath<kr.juhee.mybootboard.reply.entity.Reply, kr.juhee.mybootboard.reply.entity.QReply> replyList = this.<kr.juhee.mybootboard.reply.entity.Reply, kr.juhee.mybootboard.reply.entity.QReply>createList("replyList", kr.juhee.mybootboard.reply.entity.Reply.class, kr.juhee.mybootboard.reply.entity.QReply.class, PathInits.DIRECT2);

    public final EnumPath<kr.juhee.mybootboard.domain.Role> role = createEnum("role", kr.juhee.mybootboard.domain.Role.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

