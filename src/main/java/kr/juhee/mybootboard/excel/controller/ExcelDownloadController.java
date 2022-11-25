package kr.juhee.mybootboard.excel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.juhee.mybootboard.excel.service.ExcelService;

@Controller
@RequestMapping("/")
public class ExcelDownloadController {
	
	@Autowired
	private ExcelService excelService;
	
	@GetMapping("/download")
	public void download(HttpServletResponse response) throws IOException{
		excelService.download(response);
	}
	

}
