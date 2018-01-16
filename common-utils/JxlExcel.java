package com.sharpen.excel;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * jxl 操作 Excel
 * 
 * @author Folger
 *
 */
public class JxlExcel {

	/**
	 * 使用jxl进行写Excel操作
	 * 
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void JxlWrite() throws IOException, RowsExceededException, WriteException {
		File xlsFile = new File("C:/1/jxl.xls");
		// 创建一个工作簿
		WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
		// 创建一个工作表
		WritableSheet sheet = workbook.createSheet("test1", 0);
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				// 向工作表中添加数据
				sheet.addCell(new Label(col, row, "data" + row + col));
			}
		}
		workbook.write();
		workbook.close();
	}

	/**
	 * 使用jxl进行读Excel操作
	 * 
	 * @throws BiffException
	 * @throws IOException
	 */
	public static void JxlRead() throws BiffException, IOException {
		File xlsFile = new File("C:/1/build1.xls");
		// 获得工作簿对象
		Workbook workbook = Workbook.getWorkbook(xlsFile);
		// 获得所有工作表
		Sheet[] sheets = workbook.getSheets();
		// 遍历工作表
		if (sheets != null) {
			for (Sheet sheet : sheets) {
				// 获得行数
				int rows = sheet.getRows();
				// 获得列数
				int cols = sheet.getColumns();
				// 读取数据
				for (int row = 0; row < rows; row++) {
					for (int col = 0; col < cols; col++) {
						System.out.printf("%20s", sheet.getCell(col, row).getContents());
					}
					System.out.println();
				}
			}
			workbook.close();
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @throws IOException
	 * @throws BiffException
	 */
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException, BiffException {

		// JxlWrite();
		JxlRead();
		System.out.println("操作成功！");
	}
}
