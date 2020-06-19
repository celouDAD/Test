package dbtest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWrite {
	/**
	 * POI 创建高版本Excel文件
	 * 
	 * @author yangtingting
	 * @date 2019/07/29
	 */
	public static void main(String[] args) throws Exception {

		File file = new File("D:/p_adjust.xlsx");
		// 创建Excel文件薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = null;
		FileOutputStream stream = null;
		// 创建一个文件
		if (file.exists()) {
			workbook = new XSSFWorkbook("D:/p_adjust.xlsx");
			System.out.println("文件已存在");

			stream = FileUtils.openOutputStream(file);
			sheet = workbook.getSheet("Sheet0");
		} else {
			stream = FileUtils.openOutputStream(file);
			file.createNewFile();
			// 创建工作表sheeet
			sheet = workbook.createSheet();
			// 创建第一行
			Row row = sheet.createRow(0);
			String[] title = { "vs", "geneID", "p_adjust", "name" };
			Cell cell = null;
			for (int i = 0; i < title.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(title[i]);
			}
		}

		// 追加数据
		for (int i = sheet.getLastRowNum() + 1; i < 20; i++) {
			Row nextrow = sheet.createRow(i);
			Cell cell2 = nextrow.createCell(0);
			cell2.setCellValue("a" + i);
			cell2 = nextrow.createCell(1);
			cell2.setCellValue("user" + i);
			cell2 = nextrow.createCell(2);
			cell2.setCellValue("男");
		}

		workbook.write(stream);
		stream.close();

	}
	
	public void stat(String vs,String geneID,String p_adjust,String musName,int times) throws IOException {
		File file = new File("D:/p_adjust.xlsx");
		// 创建Excel文件薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = null;
		FileOutputStream stream = null;
		// 创建一个文件
		if (file.exists()) {
			workbook = new XSSFWorkbook("D:/p_adjust.xlsx");
			System.out.println("文件已存在");

			stream = FileUtils.openOutputStream(file);
			sheet = workbook.getSheet("Sheet0");
		} else {
			stream = FileUtils.openOutputStream(file);
			file.createNewFile();
			// 创建工作表sheeet
			sheet = workbook.createSheet();
			// 创建第一行
			Row row = sheet.createRow(0);
			String[] title = { "vs", "geneID", "p_adjust", "name" };
			Cell cell = null;
			for (int i = 0; i < title.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(title[i]);
			}
		}

		// 追加数据
		for (int i = sheet.getLastRowNum() + 1; i < times; i++) {
			Row nextrow = sheet.createRow(i);
			Cell cell2 = nextrow.createCell(0);
			cell2.setCellValue(vs);
			cell2 = nextrow.createCell(1);
			cell2.setCellValue(geneID);
			cell2 = nextrow.createCell(2);
			cell2.setCellValue(p_adjust);
			cell2 = nextrow.createCell(3);
			cell2.setCellValue(musName);
		}

		workbook.write(stream);
		stream.close();

	}
}
