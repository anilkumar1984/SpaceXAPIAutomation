package dataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {

	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	private XSSFCell cell;
	private int columnNumber;
	private String cellData;
	private FileInputStream inputStream;
	private File file;

	public ExcelFileReader(String sheetName) {

		file = new File("./TestData/TestData.xlsx");
		try {
			inputStream = new FileInputStream(file);
			wb = new XSSFWorkbook(inputStream);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		sheet = wb.getSheet(sheetName);

	}

	public String getCellData(String columnName, int rowNumber) {
		
		int columnCount = sheet.getRow(0).getLastCellNum();
		for (int j = 0; j < columnCount; j++) {
			String cellDataHeader = sheet.getRow(0).getCell(j).getStringCellValue();
			if (cellDataHeader.equalsIgnoreCase(columnName)) {
				columnNumber = j;
				break;
			}
		}

		cellData = sheet.getRow(rowNumber).getCell(columnNumber).getStringCellValue();
		//System.out.println("celldata in " + cellData);
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cellData;

	}

	public int getRowCount() {
		
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}

}
