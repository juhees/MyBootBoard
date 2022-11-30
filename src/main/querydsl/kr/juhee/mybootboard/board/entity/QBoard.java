package kr.juhee.mybootboard.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -380835327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final StringPath category = createString("category");

    public final NumberPath<Long> cnt = createNumber("cnt", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Long> fileId = createNumber("fileId", Long.class);

    public final ListPath<kr.juhee.mybootboard.file.entity.FileEntity, kr.juhee.mybootboard.file.entity.QFileEntity> fileList = this.<kr.juhee.mybootboard.file.entity.FileEntity, kr.juhee.mybootboard.file.entity.QFileEntity>createList("fileList", kr.juhee.mybootboard.file.entity.FileEntity.class, kr.juhee.mybootboard.file.entity.QFileEntity.class, PathInits.DIRECT2);

    public final kr.juhee.mybootboard.member.entity.QMember member;

    public final ListPath<kr.juhee.mybootboard.reply.entity.Reply, kr.juhee.mybootboard.reply.entity.QReply> reply = this.<kr.juhee.mybootboard.reply.entity.Reply, kr.juhee.mybootboard.reply.entity.QReply>createList("reply", kr.juhee.mybootboard.reply.entity.Reply.class, kr.juhee.mybootboard.reply.entity.QReply.class, PathInits.DIRECT2);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath title = createString("title");

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new kr.juhee.mybootboard.member.entity.QMember(forProperty("member")) : null;
    }

}

