package kr.juhee.mybootboard.file.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.juhee.mybootboard.file.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long>{
	
	@Query(value="SELECT * FROM file_entity WHERE seq = :seq" , nativeQuery = true )
	List<FileEntity> selectImageAllViewQuery(@Param("seq")Long seq); 

}
