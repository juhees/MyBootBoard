package kr.juhee.mybootboard.excel.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.juhee.mybootboard.board.entity.Board;
import kr.juhee.mybootboard.board.service.BoardService;

@Service
public class ExcelService {
	
	@Autowired
	private BoardService boardService;
    
    public void download(HttpServletResponse response) throws IOException {
        Workbook wb = new XSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("createsheet");
        int rowNum = 0;
        Cell cell = null;
        Row row = null;
 
        // Header
        int cellNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(cellNum++);
        cell.setCellValue("seq");
        cell = row.createCell(cellNum++);
        cell.setCellValue("cnt");
        cell = row.createCell(cellNum++);
        cell.setCellValue("content");
        cell = row.createCell(cellNum++);
        cell.setCellValue("create_date");
        cell = row.createCell(cellNum++);
        cell.setCellValue("title");
        cell = row.createCell(cellNum++);
        cell.setCellValue("member_id");
        cell = row.createCell(cellNum++);
        cell.setCellValue("category");
 
        // Body
        for (Board board : boardService.getExcelList()) {
            cellNum = 0;
            row = sheet.createRow(rowNum++);
            cell = row.createCell(cellNum++);
            cell.setCellValue(board.getSeq());
            cell = row.createCell(cellNum++);
            cell.setCellValue(board.getCnt());
            cell = row.createCell(cellNum++);
            cell.setCellValue(board.getContent());
            cell = row.createCell(cellNum++);
            cell.setCellValue(String.format(board.getCreateDate().toString())); //date 타입 밀리세컨으로 떠서 변환
            cell = row.createCell(cellNum++);
            cell.setCellValue(board.getTitle());
            cell = row.createCell(cellNum++);
            cell.setCellValue(board.getMember().getName());
            cell = row.createCell(cellNum++);
            cell.setCellValue(board.getCategory());
            
        }
 
        // Download
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=Board.xlsx");
        try {
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }

}
