package kr.juhee.mybootboard.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.file.entity.FileEntity;
import kr.juhee.mybootboard.file.repository.FileRepository;
import kr.juhee.mybootboard.file.service.FileService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

	@Value("${file.dir}")
	private String fileDir;
	
	private final FileRepository fileRepository;
	
	public Long saveFile(MultipartFile files, Board board) throws IOException{
		if(files.isEmpty()) {
			return null;
		}
		
		String origName=files.getOriginalFilename();
		String uuid=UUID.randomUUID().toString();
		String extension=origName.substring(origName.lastIndexOf("."));
		String savedName=uuid+extension;
		String savedPath=fileDir+savedName;
		
		FileEntity file = FileEntity.builder().orgName(origName).savedName(savedName).savedPath(savedPath).build();
		file.setBoard(board);
		files.transferTo(new File(savedPath));
		
		FileEntity savedFile = fileRepository.save(file);
		
		return savedFile.getId();
			
		
	}
	
	public List<FileEntity> view(){
		return fileRepository.findAll();
	}
	
	public FileEntity downloadImage(@PathVariable("fileId")Long id) {
		return fileRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<FileEntity> fileAllView(Long seq){
		System.out.println("=====>list");
		return fileRepository.selectImageAllViewQuery(seq);
	}
	
	

}
