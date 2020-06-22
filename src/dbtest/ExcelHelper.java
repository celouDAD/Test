package dbtest;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Excel工具类
 * 
 * @author lwt creat at Jun 23, 2020
 *
 */
public class ExcelHelper {

	public static void main(String[] args) throws Exception {
		ExcelHelper eh = new ExcelHelper();
		//eh.readExecl("./source/p_adjust.xlsx");
		eh.writeExcel("vs", "geneID", "p_adjust", "2223", 5);
		//eh.addExcel("vs", "geneID", "p_adjust", "2023", 5);

	}

	/**
	 * 读取excel
	 * 
	 * @param path
	 */
	public void readExecl(String path) {
		try {
			// 读取的时候可以使用流，也可以直接使用文件名
			XSSFWorkbook xwb = new XSSFWorkbook(path);
			// 循环工作表sheet
			for (int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++) {
				XSSFSheet xSheet = xwb.getSheetAt(numSheet);
				if (xSheet == null) {
					continue;
				}
				// 循环行row
				for (int numRow = 0; numRow <= xSheet.getLastRowNum(); numRow++) {
					XSSFRow xRow = xSheet.getRow(numRow);
					if (xRow == null) {
						continue;
					}
					// 循环列cell
					for (int numCell = 0; numCell <= xRow.getLastCellNum(); numCell++) {
						XSSFCell xCell = xRow.getCell(numCell);
						if (xCell == null) {
							continue;
						}
						// 输出值
						System.out.println("excel表格中取出的数据" + getValue(xCell));
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取出每列的值
	 *
	 * @param xCell 列
	 * @return
	 */
	private String getValue(XSSFCell xCell) {
		if (xCell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(xCell.getBooleanCellValue());
		} else if (xCell.getCellType() == CellType.NUMERIC) {
			return String.valueOf(xCell.getNumericCellValue());
		} else if (xCell.getCellType() == CellType.FORMULA) {
			return String.valueOf(xCell.getCellFormula());
		} else {
			return String.valueOf(xCell.getStringCellValue());
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean fileExist(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		flag = file.exists();
		return flag;
	}

	/**
	 * 写入Excel
	 * 
	 * @param vs
	 * @param geneID
	 * @param p_adjust
	 * @param musName
	 * @param times
	 * @param filePath
	 */
	public static void writeExcel(String vs, String geneID, String p_adjust, String musName, int times) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("sheet");
		XSSFRow firstRow = sheet.createRow(0);// 第一行表头
		XSSFCell cells[] = new XSSFCell[4];

		String[] titles = new String[] { "vs", "geneID", "p_adjust", "musName" };
		// 循环设置表头信息
		for (int i = 0; i < 4; i++) {
			cells[0] = firstRow.createCell(i);
			cells[0].setCellValue(titles[i]);
		}

		// 追加数据
		for (int i = 0; i < times; i++) {
			Row nextrow = sheet.createRow(sheet.getLastRowNum()+1);
			Cell cell2 = nextrow.createCell(0);
			cell2.setCellValue(vs);
			cell2 = nextrow.createCell(1);
			cell2.setCellValue(geneID);
			cell2 = nextrow.createCell(2);
			cell2.setCellValue(p_adjust);
			cell2 = nextrow.createCell(3);
			cell2.setCellValue(musName);
		}
		OutputStream out = null;
		try {
			out = new FileOutputStream("./source/p_adjust.xlsx");
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 追加行
	 * @param vs
	 * @param geneID
	 * @param p_adjust
	 * @param musName
	 * @param times
	 * @throws IOException
	 */
	public static void addExcel(String vs, String geneID, String p_adjust, String musName, int times)
			throws IOException {
		FileInputStream in = new FileInputStream("./source/p_adjust.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(0);
		System.out.println(sheet.getSheetName());

		FileOutputStream out = new FileOutputStream("./source/p_adjust.xlsx");
		// 从第二行开始追加列
		/*
		 * row=sheet.getRow(1); row.createCell(3).setCellValue("AAA");
		 * row.createCell(4).setCellValue("BBB");
		 */
		System.out.println(sheet.getLastRowNum());
		// 追加数据
		for (int i = 0; i < times; i++) {
			System.out.println(times);
			Row nextrow = sheet.createRow(sheet.getLastRowNum()+1);
			Cell cell2 = nextrow.createCell(0);
			cell2.setCellValue(vs);
			cell2 = nextrow.createCell(1);
			cell2.setCellValue(geneID);
			cell2 = nextrow.createCell(2);
			cell2.setCellValue(p_adjust);
			cell2 = nextrow.createCell(3);
			cell2.setCellValue(musName);
		}

		try {
			out.flush();
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
