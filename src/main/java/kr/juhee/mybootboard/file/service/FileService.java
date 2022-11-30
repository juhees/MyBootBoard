package kr.juhee.mybootboard.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.file.entity.FileEntity;

public interface FileService {
	
	Long saveFile(MultipartFile files, Board board)throws IOException;
	
	List <FileEntity> view();
	
	List<FileEntity> fileAllView(Long seq);
	
	FileEntity downloadImage(@PathVariable("fileId")Long id);

}
