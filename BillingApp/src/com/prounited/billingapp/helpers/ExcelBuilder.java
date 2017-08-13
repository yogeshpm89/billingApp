package com.prounited.billingapp.helpers;

import java.awt.Font;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.prounited.billingapp.models.Category;

public class ExcelBuilder extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		// get data model which is passed by the Spring container
		Map<String, Object> modelMap = (Map<String, Object>) model.get("modelMap");
        List list = (List) modelMap.get("list");
        List colList = (List) modelMap.get("colList");
        String grid = (String) modelMap.get("grid");
        Class entityClass = (Class) modelMap.get("entityClass");
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet(grid);
        //sheet.setDefaultColumnWidth(10);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.GREEN.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderTop((short) 1);
        style.setBorderRight((short) 1);
        style.setBorderBottom((short) 1);
        style.setBorderLeft((short) 1);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        
        // create header row
        HSSFRow header = sheet.createRow(0);
        Method[] methodArray = entityClass.getDeclaredMethods();
        
        List<String> dataTypes = new ArrayList<String>();
		dataTypes.add("String");
		dataTypes.add("Date");
		dataTypes.add("long");
		dataTypes.add("int");
		dataTypes.add("float");
		dataTypes.add("double");
		int colIndex = 0;
		if(colList != null) {
			for(Object column: colList) {
				String columnHeader = column.toString();
				int col = colIndex++;
				header.createCell(col).setCellValue(columnHeader.toUpperCase());
		        header.getCell(col).setCellStyle(style);
		        sheet.autoSizeColumn(col);
			}
		} else {
			for (Method method: methodArray) {
				if(method.getName().startsWith("get") && 
						dataTypes.contains(method.getReturnType().getSimpleName())) {
					int col = colIndex++;
					header.createCell(col).setCellValue(method.getName().replace("get", ""));
			        header.getCell(col).setCellStyle(style);
				}
			}
		}
		// create data rows
        int rowCount = 0;
        for (int i=0; i<list.size(); i++) {
        	Object class1 = entityClass.cast(list.get(i));
        	//System.out.println(class1.toString());
            rowCount++;
            colIndex = 0;
            HSSFRow aRow = sheet.createRow(rowCount);
            if(colList != null) {
            	Map<String, Method> methodMap = new HashMap<String, Method>();
            	for (Method method: methodArray) {
            		methodMap.put(method.getName().toLowerCase(), method);
            	}
    			for(Object column: colList) {
    				String columnHeader = column.toString();
    				int col = colIndex++;
    				String value = "";
    				Method method = methodMap.get("get" + columnHeader.toLowerCase().replace(" ", ""));
    				Cell cell =  aRow.createCell(col);
    				
    				// create style for header cells
    		        CellStyle cellStyle = workbook.createCellStyle();
    		        cellStyle.setBorderTop((short) 1);
    		        cellStyle.setBorderRight((short) 1);
    		        cellStyle.setBorderBottom((short) 1);
    		        cellStyle.setBorderLeft((short) 1);
    		        
    				if ("Date".equalsIgnoreCase(method.getReturnType().getSimpleName())) {
						Date date = (Date) method.invoke(class1, null);
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						if (date != null) {
							value = dateFormat.format(date);
							cell.setCellType(Cell.CELL_TYPE_STRING);
							cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
							cell.setCellValue(value);
						}
					} else if("Long".equalsIgnoreCase(method.getReturnType().getSimpleName())) {
						Long long1 = (Long) method.invoke(class1, null);
						if (long1 != null) {
							value = long1.toString();
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
							cell.setCellValue(long1);
						}
					} else if("float".equalsIgnoreCase(method.getReturnType().getSimpleName())) {
						Float float1 = (Float) method.invoke(class1, null);
						if (float1 != null) {
							value = float1.toString();
							cell.setCellType(Cell.CELL_TYPE_NUMERIC);
							cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
							cell.setCellValue(float1);
						}
					} else {
						value = (String) method.invoke(class1, null);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
						cell.setCellValue(value);
					}
    				cell.setCellStyle(cellStyle);
    			}
    		} else {
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
						} else if("float".equalsIgnoreCase(method.getReturnType().getSimpleName())) {
							Float float1 = (Float) method.invoke(class1, null);
							if (float1 != null) {
								value = float1.toString();
							}
						} else {
							value = (String) method.invoke(class1, null);
						}
						 //System.out.println(rowCount + " : " + colIndex + " : " + value);
				         aRow.createCell(colIndex++).setCellValue(value);
					}
            	}
				//System.out.println(rowCount + "-----------------");
			}
        }
	}
}
