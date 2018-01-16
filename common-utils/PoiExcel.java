package com.sharpen.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * poi 操作 Excel
 * 
 * @author Folger
 *
 */
public class PoiExcel {

	/**
	 * poi 写Excel数据
	 * 
	 * @throws IOException
	 */
	public static void PoiWrite() throws IOException {

		// 创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建工作表
		HSSFSheet sheet = workbook.createSheet("test1");

		for (int row = 0; row < 10; row++) {
			HSSFRow rows = sheet.createRow(row);
			for (int col = 0; col < 10; col++) {
				// 向工作表中添加数据
				rows.createCell(col).setCellValue("data" + row + col);

			}
		}
		File xlsFile = new File("C:/1/poi.xls");
		FileOutputStream xlsStream = new FileOutputStream(xlsFile);
		workbook.write(xlsStream);
		// 关闭操作
		workbook.close();
	}

	/**
	 * poi 读 Excel数据
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws EncryptedDocumentException
	 */
	public static void PoiRead() throws EncryptedDocumentException, InvalidFormatException, IOException {

		File xlsFile = new File("C:/1/poi.xls");
		// 获得工作簿
		Workbook workbook = WorkbookFactory.create(xlsFile);
		// 获得工作表个数
		int sheetCount = workbook.getNumberOfSheets();
		// 遍历工作表
		for (int i = 0; i < sheetCount; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			// 获得行数
			int rows = sheet.getLastRowNum() + 1;
			// 获得列数 先获得一行，再得到行列数
			Row tmp = sheet.getRow(0);
			if (tmp == null) {
				continue;
			}
			int cols = tmp.getPhysicalNumberOfCells();
			// 读取数据
			for (int row = 0; row < rows; row++) {
				Row r = sheet.getRow(row);
				for (int col = 0; col < cols; col++) {
					System.out.printf("%10s", r.getCell(col).getStringCellValue());
				}
				System.out.println();
			}

		}
		// 关闭操作
		workbook.close();
	}

	/**
	 * 测试
	 * 
	 * @param args
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 */
	public static void main(String[] args) throws IOException, EncryptedDocumentException, InvalidFormatException {
		// PoiWrite();
		PoiRead();
		System.out.println("操作成功！");
	}
}
