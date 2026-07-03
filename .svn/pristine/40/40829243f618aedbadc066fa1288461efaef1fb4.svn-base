package com.stl.ngms.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class ExcelGenerator {
	final static Logger logger = Logger.getLogger(ExcelGenerator.class);
	
	public static String exportToExcel(List<Map<String, Object>> data,String filePah, String excelName) throws IOException {
		logger.info("@ @ @ @ @ @----Excel Started----@ @ @ @ @ @");
		//int i=0;
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		SXSSFSheet spreadsheet = workbook.createSheet("Allocated_Projects");
		XSSFCellStyle style = workbook.getXSSFWorkbook().createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
		SXSSFRow row;
		SXSSFRow headRow=null;
		int rowid=0;
		int header=0;

		
		for (Map<String,Object> map : data) {
			int cellid=0;
			Set<String> set=map.keySet();
			if(rowid==0)
			{
				headRow = spreadsheet.createRow(rowid++);
				for (String key : set) {
					Cell cell = headRow.createCell(cellid++);
					cell.setCellValue(key);
					header++;
					if(header>set.size()) {
						break;
					}
				}
			}

			cellid=0;
			row = spreadsheet.createRow(rowid++);
			for (String key : set) {
				Cell cell = row.createCell(cellid++);
				String value = null;
				try {
					value=map.get(key).toString();
				} catch (NullPointerException nex) {
					value=" ";
					//System.err.print(i++);
				}
				if(StringUtils.isEmpty(value)) {
					cell.setCellValue(" ");
				}else {
					try {
					Integer.parseInt(value);
					cell.setCellStyle(style);
					cell.setCellValue(Integer.parseInt(value));
					}catch(NumberFormatException nfe) {
						cell.setCellValue(value);
						//logger.info(nfe.getMessage());
					}
				}
			}
		}

		
		int count=0;
		for (Map<String,Object> map : data) {
			Set<String> set=map.keySet();
			if(count==0)
			{
				for (String key : set) {
					logger.info(count);
					spreadsheet.autoSizeColumn(count);
					count++;
					if(count>set.size()) {
						break;
					}
				}
			}
		}
		
		logger.info("Column no= "+data.size());

		FileOutputStream out;
		File file=null;
		try {
			file=new File(filePah+excelName+"_"+new Date().getTime()+".xlsx");
			/*File file=new File("/home/opepa/SMA/excel/"+fileName+".xlsx");*/
			out = new FileOutputStream(file);
			logger.info(file.getName());
			//fileName=file.getName();
			workbook.write(out);
			workbook.close();
			out.close();
			logger.info("@ @ @ @ @ @----Excel Ended----@ @ @ @ @ @");
			return file.getName();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info("@ @ @ @ @ @----Excel Ended----@ @ @ @ @ @");
			return file.getName();
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("@ @ @ @ @ @----Excel Ended----@ @ @ @ @ @");
			return file.getName();
		}
		//logger.info(fileName);
		//return excelPath+fileName;		
	}
	
}
