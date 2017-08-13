package com.prounited.billingapp.services.impl;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.metamodel.source.annotations.entity.EntityClass;

import com.prounited.billingapp.models.Customer;
import com.prounited.billingapp.services.ExcelExportService;
import com.prounited.billingapp.vos.CustomerVO;

public class ExcelExportServiceImpl implements ExcelExportService {

	@Override
	public <T> byte[] writeListToExcel(String fileName, List<T> list,
			Class<T> entityClass) throws Exception {
		
		Workbook workbook = null;

		if (fileName.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (fileName.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new Exception("invalid file name, should be xls or xlsx");
		}

		Sheet sheet = workbook.createSheet(entityClass.getName());

		Iterator<T> iterator = list.iterator();
		List<String> dataTypes = new ArrayList<String>();
		dataTypes.add("String");
		dataTypes.add("Date");
		dataTypes.add("long");
		dataTypes.add("int");
		dataTypes.add("float");
		dataTypes.add("double");
		
		int rowIndex = 0;
		int colIndex = 0;
		Row row = sheet.createRow(rowIndex++);
		
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		
		Method[] methodArray = entityClass.getDeclaredMethods();
		for (Method method: methodArray) {
			if(method.getName().startsWith("get") && 
					dataTypes.contains(method.getReturnType().getSimpleName())) {
				int col = colIndex++;
				sheet.autoSizeColumn(col);
				sheet.setColumnWidth(col, 20*256);
				Cell cell = row.createCell(col);
				cell.setCellStyle(style);
				cell.setCellValue(method.getName().replace("get", ""));
			}
		}
		
		while (iterator.hasNext()) {
			T class1 = iterator.next();
			colIndex = 0;
			row = sheet.createRow(rowIndex++);
			for (Method method: methodArray) {
				if(method.getName().startsWith("get") && 
						dataTypes.contains(method.getReturnType().getSimpleName())) {
					String value = "";
					if ("Date".equalsIgnoreCase(method.getReturnType().getSimpleName())) {
						Date date = (Date) method.invoke(class1, null);
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						if (date != null) {
							value = dateFormat.format(date);
						}
					} else if("Long".equalsIgnoreCase(method.getReturnType().getSimpleName())) {
						Long long1 = (Long) method.invoke(class1, null);
						if (long1 != null) {
							value = long1.toString();
						}
					} else {
						value = (String) method.invoke(class1, null);
					}
					Cell cell = row.createCell(colIndex++);
					cell.setCellValue(value);
				}
			}
			System.out.println("-------------------------------------------------------------------");
		}

		// lets write the excel data to file now
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();
		System.out.println(fileName + " written successfully");
		return null;
		
	}
	
	
	public static void main(String[] args) {
			ExcelExportService excelExportService = new ExcelExportServiceImpl();
			List<Customer> list = new ArrayList<Customer>();
			list.add(
					new Customer(2, "Yogesh", new Date(), "Murdeshwar", "yogeshpm89@gmail.com", "Punw", "9561700270")
					);
			try {
				excelExportService.writeListToExcel("R:\\Data\\Test.xlsx", list, Customer.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public byte[] downloadExcel(String itemJsonString) {
		// TODO Auto-generated method stub
		return null;
	}
}
