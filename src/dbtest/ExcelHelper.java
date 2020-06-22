package dbtest;

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
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelHelper {

	public static void main(String[] args) throws Exception {
		ExcelHelper eh = new ExcelHelper();
		eh.readExecl("D:\\p_adjust.xlsx");
		//eh.stat("vs", "geneID", "p_adjust", "222", 5);

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
		} else if(xCell.getCellType() == CellType.FORMULA) {
			return String.valueOf(xCell.getCellFormula());
		}else {
			return String.valueOf(xCell.getStringCellValue());
		}
	}

	/**
	 * 修改表格
	 * 
	 * @param path
	 */
	public void writeExcel3(String path) {
		try {
			// 传入的文件
			FileInputStream fileInput = new FileInputStream(path);
			// poi包下的类读取excel文件

			// 创建一个webbook，对应一个Excel文件
			XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
			// 对应Excel文件中的sheet，0代表第一个
			XSSFSheet sh = workbook.getSheetAt(0);
			// 修改excle表的第5行，从第三列开始的数据
			for (int i = 2; i < 4; i++) {
				// 对第五行的数据修改
				sh.getRow(4).getCell((short) i).setCellValue(100210 + i);
			}
			// 将修改后的文件写出到D:\\excel目录下
			FileOutputStream os = new FileOutputStream("D:\\修改后test.xlsx");
			// FileOutputStream os = new
			// FileOutputStream("D:\\test.xlsx");//此路径也可写修改前的路径，相当于在原来excel文档上修改
			os.flush();
			// 将Excel写出
			workbook.write(os);
			// 关闭流
			fileInput.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stat(String vs, String geneID, String p_adjust, String musName, int times) throws IOException {
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
