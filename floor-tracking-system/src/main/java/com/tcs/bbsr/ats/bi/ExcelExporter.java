package com.tcs.bbsr.ats.bi;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;

import com.tcs.bbsr.ats.domain.AssociateInfo;
import com.tcs.bbsr.ats.domain.AssociateInfoMin;

public class ExcelExporter {

	Map<String, CellStyle> defaultCellStyles;
	
	public byte[] export(List<AssociateInfoMin> result) {
		
		Workbook book = new HSSFWorkbook();
		Sheet createSheet = initialiseSheet(book);
		
		creatHeaderCell(result, createSheet);
		createDataCells(result, createSheet);
		ByteArrayOutputStream byteArrayOutputStream = serializeData(book);
		
		return byteArrayOutputStream.toByteArray();
	}



	protected Sheet initialiseSheet(Workbook book) {
		Sheet createSheet = book.createSheet("Search Results");
		createSheet.setDisplayGridlines(false);
		
		createDefaultCellStyles(book);
		return createSheet;
	}



	protected ByteArrayOutputStream serializeData(Workbook book) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		try {
			book.write(byteArrayOutputStream);
			byteArrayOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return byteArrayOutputStream;
	}
	
	

	private void createDefaultCellStyles(Workbook book) {

		defaultCellStyles = new HashMap<String, CellStyle>();
		Font defaultFont = book.createFont();
		defaultFont.setFontName("Calibri");
		defaultFont.setFontHeightInPoints((short)11);
		
		Font boldFont = book.createFont();
		boldFont.setFontName("Calibri");
		boldFont.setFontHeightInPoints((short)11);
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		CellStyle defaultStyle = book.createCellStyle();
		defaultStyle.setFont(defaultFont);
		defaultStyle.setBorderBottom(CellStyle.BORDER_THIN);
		defaultStyle.setBorderTop(CellStyle.BORDER_THIN);
		defaultStyle.setBorderLeft(CellStyle.BORDER_THIN);
		defaultStyle.setBorderRight(CellStyle.BORDER_THIN);
		
		defaultCellStyles.put("EVEN_ROW_STYLE", defaultStyle);
		
		CellStyle headerStyle = book.createCellStyle();
		headerStyle.cloneStyleFrom(defaultStyle);
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerStyle.setFont(boldFont);
		
		defaultCellStyles.put("HEADER_ROW_STYLE", headerStyle);
		
		CellStyle oddRowStyle = book.createCellStyle();
		oddRowStyle.cloneStyleFrom(defaultStyle);
		oddRowStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		oddRowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		defaultCellStyles.put("ODD_ROW_STYLE", oddRowStyle);
		
	}

	private void createDataCells(List<AssociateInfoMin> result,
			Sheet createSheet) {
		if (CollectionUtils.isEmpty(result)) return;
		else {
			int rowCount = 0;
			for (AssociateInfoMin anAssociate : result) {
				Row createRow = createSheet.createRow(1 + rowCount);
				int cellType = (rowCount % 2 == 0)? EVEN_ROW : ODD_ROW;
				
				createCell(createRow, 0, anAssociate.getEmployeeId(), cellType);
				createCell(createRow, 1, anAssociate.getEmployeeName(), cellType);
				createCell(createRow, 2, anAssociate.getDesignation(), cellType);
				createCell(createRow, 3, anAssociate.getEmailId(), cellType);
				createCell(createRow, 4, anAssociate.getSeatNo(), cellType);
				createCell(createRow, 5, anAssociate.getAssetId1(), cellType);
				createCell(createRow, 6, anAssociate.getExtNo(), cellType);
				createCell(createRow, 7, anAssociate.getProjectName(), cellType);
				createCell(createRow, 8, anAssociate.getFriendlyShift(), cellType);
				if(anAssociate instanceof AssociateInfo) {
					AssociateInfo info = (AssociateInfo) anAssociate;
					createCell(createRow, 9, info.getCardNo(), cellType);
					createCell(createRow, 10, info.getWonSwonNo(), cellType);
					createCell(createRow, 11, info.getMobileNo(), cellType);
					createCell(createRow, 12, info.getBuilding(), cellType);
					createCell(createRow, 13, info.getFloor(), cellType);
					createCell(createRow, 14, info.getWing(), cellType);
					createCell(createRow, 15, info.getProjectId(), cellType);
					createCell(createRow, 16, info.getOccupiedFromDate(), cellType);
					createCell(createRow, 17, info.getAllocationTillDate(), cellType);
					createCell(createRow, 18, info.getClientGroup(), cellType);
					createCell(createRow, 19, info.getUnitISU(), cellType);
					createCell(createRow, 20, info.getSubUnitSubISU(), cellType);
					createCell(createRow, 21, info.getUnallocated(), cellType);
					createCell(createRow, 22, info.getBaseBranch(), cellType);
					createCell(createRow, 23, info.getManager(), cellType);
				}
				rowCount ++;
			}
			//System.err.println(rowCount);
			Row row = createSheet.getRow(0);
			short lastCellNum = row.getLastCellNum();
			for(short count = 0; count < lastCellNum; count ++) {
				createSheet.autoSizeColumn(count);
			}
		}
		
		
	}

	final int HEADER_ROW = 0x4345;
	final int ODD_ROW = 0x2365;
	final int EVEN_ROW = 0x2985;
	



	protected void createCell(Row createRow, int i, Object cellValue, int rowType) {
		
		Cell createCell = createRow.createCell(i);
		if(null == cellValue) {
			createCell.setCellValue("");
		} else if (cellValue instanceof Integer) {
			
			Integer integer = (Integer) cellValue;
			createCell.setCellValue(integer.intValue());
		} else if(cellValue instanceof Double) {
			Double double1 = (Double) cellValue;
			createCell.setCellValue(double1.doubleValue());
		} else {
			createCell.setCellValue(cellValue.toString());
		}
		// Apply background
		switch (rowType) {
			case HEADER_ROW:
				createCell.setCellStyle(defaultCellStyles.get("HEADER_ROW_STYLE"));
				break;
			case ODD_ROW:
				createCell.setCellStyle(defaultCellStyles.get("ODD_ROW_STYLE"));
				break;
			case EVEN_ROW:
				createCell.setCellStyle(defaultCellStyles.get("EVEN_ROW_STYLE"));
				break;
			default:
				createCell.setCellStyle(defaultCellStyles.get("EVEN_ROW_STYLE"));
				break;
		}
		
	}

	private void creatHeaderCell(List<AssociateInfoMin> result,
			Sheet createSheet) {
		if(CollectionUtils.isEmpty(result)) return;
		else {
			AssociateInfoMin anAssociate = result.get(0);
			Row createRow = createSheet.createRow(0);
			for (int i = 0; i < minheaders.length; i++) {
				String aValue = minheaders[i];
				createCell(createRow, i, aValue, HEADER_ROW);
			}
			int length = minheaders.length;
			if(anAssociate instanceof AssociateInfo) {
				for (int i = 0; i < otherheaders.length; i++) {
					String aValue = otherheaders[i];
					createCell(createRow, length + i, aValue, HEADER_ROW);
				}
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("new sheet");

		// Create a row and put some cells in it. Rows are 0 based.
		Row row = sheet.createRow((short) 1);

		// Aqua background
		CellStyle style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(CellStyle.BIG_SPOTS);
		Cell cell = row.createCell((short) 1);
		cell.setCellValue("X");
		cell.setCellStyle(style);

		// Orange "foreground", foreground being the fill foreground not the font color.
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell = row.createCell((short) 2);
		cell.setCellValue("X");
		cell.setCellStyle(style);

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("C:/Test/workbook1.xls");
		wb.write(fileOut);
		fileOut.close();
	}
	private String[] minheaders = {"Employee Id", "Employee Name", "Designation", "Email", "Seat No",
									"Asset Id", "Extension", "Project", "Shift"};
	
	private String[] otherheaders = {"Card No",	"WON/SWON",	"MobileNumber",	"Building",	"Floor",
			"Wing",	"Project ID",	"Occupancy From",	"Allocation Till","Client Group", "Unit/ISU",
			"Sub Unit/ISU",	"Is Unallocated", "Base Branch", "Manager"}; 
}
