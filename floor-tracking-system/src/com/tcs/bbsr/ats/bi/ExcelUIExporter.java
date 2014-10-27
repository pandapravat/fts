package com.tcs.bbsr.ats.bi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.util.CollectionUtils;

public class ExcelUIExporter extends ExcelExporter {
	
	public byte[] exportData(String tabledata)  {
		Workbook book = new HSSFWorkbook();
		
		SAXBuilder builder = new SAXBuilder();
		Sheet createSheet = initialiseSheet(book);
		try {
			org.jdom.Document build = builder.build(new StringReader(tabledata));
			creatHeaderCell(build, createSheet);
			createDataCells(build, createSheet);
		} catch (Exception e) {
			throw new AtsException("Error during exporting", e);
		}
		
		ByteArrayOutputStream byteArrayOutputStream = serializeData(book);
		
		return byteArrayOutputStream.toByteArray();
	}

	public static void main(String[] args) {
		String data = "<table><headers><header>Employee Id</header><header>Employee Name</header><header>Designation</header><header>Email</header><header>Seat No</header><header>Asset Id</header><header>Extension</header><header>Project</header><header>Shift</header><header>Card No</header><header>WON/SWON</header><header>MobileNumber</header><header>Building</header><header>Floor</header><header>Wing</header><header>Project ID</header><header>Occupancy From</header><header>Allocation Till</header><header>Client Group</header><header>Unit/ISU</header><header>Sub Unit/ISU</header><header>Is Unallocated</header><header>Base Branch</header><header>Manager</header></headers><datas><aRowData><aCell>391461</aCell><aCell>Rounak Shah</aCell><aCell>ASE</aCell><aCell>Rounak.shah@tcs.com</aCell><aCell>2A003</aCell><aCell>TCS114886</aCell><aCell>5182</aCell><aCell>QA-NSO</aCell><aCell>A</aCell><aCell>348640</aCell><aCell>9338314751</aCell><aCell>2179788</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-PQ-SOS-BHU-US-OFF</aCell><aCell>40394</aCell><aCell>31/12/2011</aCell><aCell>AC Nielsen</aCell><aCell>IS-Retail & CPG1-Parent</aCell><aCell>IS-Retail & CPG1.4-Group4</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>abc</aCell></aRowData><aRowData><aCell>513341</aCell><aCell>Suman Pani</aCell><aCell>ASE-T</aCell><aCell>suman.pani@tcs.com</aCell><aCell>2A011</aCell><aCell>TCS107752</aCell><aCell>5185</aCell><aCell>QA-NSO</aCell><aCell>A</aCell><aCell>407438</aCell><aCell>9861436708</aCell><aCell>2179788</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-PQ-SOS-BHU-US-OFF</aCell><aCell>40889</aCell><aCell>31/12/2011</aCell><aCell>AC Nielsen</aCell><aCell>IS-Retail & CPG1-Parent</aCell><aCell>IS-Retail & CPG1.4-Group4</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>334683</aCell><aCell>S Padmaja</aCell><aCell>SE</aCell><aCell>s.padmaja@tcs.com</aCell><aCell>2A028</aCell><aCell>null</aCell><aCell>5190</aCell><aCell>QA-NSO</aCell><aCell>A</aCell><aCell>278758</aCell><aCell>9338511461</aCell><aCell>2179788</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-PQ-SOS-BHU-US-OFF</aCell><aCell>40763</aCell><aCell>31/12/2011</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>330822</aCell><aCell>Santwana sarangi</aCell><aCell>SE</aCell><aCell>Santwana.sarangi@tcs.com</aCell><aCell>2A045</aCell><aCell>TCS/107721 </aCell><aCell>5197</aCell><aCell>QA-NSO</aCell><aCell>A</aCell><aCell>273296</aCell><aCell>8984167950</aCell><aCell>2179788</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-PQ-SOS-BHU-US-OFF</aCell><aCell>40889</aCell><aCell>31/12/2011</aCell><aCell>AC Nielsen</aCell><aCell>IS-Retail & CPG1-Parent</aCell><aCell>IS-Retail & CPG1.4-Group4</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>513336</aCell><aCell>Sanghamitra Purohita</aCell><aCell>ASE-T</aCell><aCell>Sanghamitra.Purohita@tcs.com</aCell><aCell>2A034</aCell><aCell>01hw259868</aCell><aCell>5191</aCell><aCell>QA-NSO</aCell><aCell>A</aCell><aCell>407433</aCell><aCell>9778154467</aCell><aCell>2179788</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-PQ-SOS-BHU-US-OFF</aCell><aCell>40889</aCell><aCell>31/12/2011</aCell><aCell>AC Nielsen</aCell><aCell>IS-Retail & CPG1-Parent</aCell><aCell>IS-Retail & CPG1.4-Group4</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>376217</aCell><aCell>Avishek Mazumder</aCell><aCell>SE</aCell><aCell>avishek.mazumder@tcs.com</aCell><aCell>2A038</aCell><aCell>Tcs/114783</aCell><aCell>5193</aCell><aCell>QA-NEG</aCell><aCell>A</aCell><aCell>333073</aCell><aCell>9937263916</aCell><aCell>2168602</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-Media Engg-US-QA-Off</aCell><aCell>13/12/2010</aCell><aCell>31/12/2011</aCell><aCell>AC Nielsen</aCell><aCell>IS-Retail & CPG1-Parent</aCell><aCell>IS-Retail & CPG1.4-Group4</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>453667</aCell><aCell>Pragnya Satapathy</aCell><aCell>ASE</aCell><aCell>pragnya.satapathy@tcs.com</aCell><aCell>2A039</aCell><aCell>null</aCell><aCell>5208</aCell><aCell>QA-BDN</aCell><aCell>A</aCell><aCell>0</aCell><aCell>9437807921</aCell><aCell>2179788</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-PQ-SOS-BHU-US-OFF</aCell><aCell>22/09/2011</aCell><aCell>31/12/2012</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>181061</aCell><aCell>Debi Prasad Mishra</aCell><aCell>SE</aCell><aCell>debi.mishra@tcs.com</aCell><aCell>2A044</aCell><aCell>01hw/186018 </aCell><aCell>5192</aCell><aCell>NTS</aCell><aCell>A</aCell><aCell>80297</aCell><aCell>9439490818</aCell><aCell>2150343</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-ADM-SDO-MED-US-OFF</aCell><aCell>40183</aCell><aCell>31/12/2011</aCell><aCell>AC Nielsen</aCell><aCell>IS-Retail & CPG1-Parent</aCell><aCell>IS-Retail & CPG1.4-Group4</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>476732</aCell><aCell>Manas Mangraj</aCell><aCell>ASE</aCell><aCell>manas.mangaraj@tcs.com</aCell><aCell>2A050</aCell><aCell>null</aCell><aCell>0</aCell><aCell>QA-NSO</aCell><aCell>A</aCell><aCell>235062</aCell><aCell>9861395511</aCell><aCell>0</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-PQ-SOS-BHU-US-OFF</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>398846</aCell><aCell>Sahasranshu Pattnaik</aCell><aCell>ASE</aCell><aCell>sahasranshu.pattnaik@tcs.com</aCell><aCell>2A053</aCell><aCell>null</aCell><aCell>5196</aCell><aCell>QA-NSO</aCell><aCell>A</aCell><aCell>355588</aCell><aCell>9583431916</aCell><aCell>2179788</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-PQ-SOS-BHU-US-OFF</aCell><aCell>40763</aCell><aCell>31/12/2011</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>496554</aCell><aCell>SAYONI SETH</aCell><aCell>null</aCell><aCell>sayoni.seth@tcs.com</aCell><aCell>2A055</aCell><aCell>null</aCell><aCell>0</aCell><aCell>NSO-SUPPORT</aCell><aCell>A</aCell><aCell>400675</aCell><aCell>null</aCell><aCell>2187035</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-SP-TRANS-US-OFF-PROGRAMS</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>305218</aCell><aCell>PANKAJ PARASAR</aCell><aCell>SE</aCell><aCell>pankaj.parasar@tcs.com</aCell><aCell>2A066</aCell><aCell>01HW259981</aCell><aCell>5201</aCell><aCell>QA-AWAF</aCell><aCell>A</aCell><aCell>231800</aCell><aCell>8895212784</aCell><aCell>2179788</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-PQ-SOS-BHU-US-OFF</aCell><aCell>40700</aCell><aCell>31/12/2011</aCell><aCell>AC Nielsen</aCell><aCell>IS-Retail & CPG1-Parent</aCell><aCell>IS-Retail & CPG1.4-Group4</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>496967</aCell><aCell>SUBHAJIT PRATIHAR</aCell><aCell>ASE-T</aCell><aCell>SUBHAJIT.PRATIHAR@tcs.com</aCell><aCell>2A074</aCell><aCell>null</aCell><aCell>5201</aCell><aCell>NSO-SUPPORT</aCell><aCell>A</aCell><aCell>0</aCell><aCell>7381964857</aCell><aCell>2181119</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>TNC-ADM-GPS-PLTFM2-SD-US-BHU-O</aCell><aCell>31/1/2011</aCell><aCell>31/12/2011</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>No</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>356029</aCell><aCell>Monalisha Mohanty</aCell><aCell>null</aCell><aCell>null</aCell><aCell>Floater</aCell><aCell>null</aCell><aCell>0</aCell><aCell>Buzz Metrics</aCell><aCell>A</aCell><aCell>0</aCell><aCell>null</aCell><aCell>0</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>Buzz Metrics</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>null</aCell><aCell>TCS - Bhubaneswar</aCell><aCell>null</aCell></aRowData><aRowData><aCell>126858</aCell><aCell>Susant Singh</aCell><aCell></aCell><aCell></aCell><aCell>Floater</aCell><aCell></aCell><aCell>0</aCell><aCell>AOD</aCell><aCell>A</aCell><aCell>0</aCell><aCell></aCell><aCell>0</aCell><aCell>Kalinga Park</aCell><aCell>1</aCell><aCell>A</aCell><aCell>AOD</aCell><aCell></aCell><aCell></aCell><aCell></aCell><aCell></aCell><aCell></aCell><aCell></aCell><aCell>TCS - Bhubaneswar</aCell><aCell></aCell></aRowData></datas></table>";
		SAXBuilder builder = new SAXBuilder();
		try {
			org.jdom.Document build = builder.build(new StringReader(data));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
	}
	@SuppressWarnings("rawtypes")
	private void createDataCells(org.jdom.Document build, Sheet workSheet) {
		org.jdom.Element rootElement = build.getRootElement();
		org.jdom.Element child = rootElement.getChild("datas");
		if(null != child) {
			List children = child.getChildren("aRowData");
			if(!CollectionUtils.isEmpty(children)) {
				int rowCount=1;
				for (Object object : children) {
					Row createRow = workSheet.createRow(rowCount);
					if (object instanceof org.jdom.Element) {
						org.jdom.Element element = (org.jdom.Element) object;

						List allCells = element.getChildren("aCell");
						int cellType = (rowCount % 2 == 0)? EVEN_ROW : ODD_ROW;
						int colCount = 0;
						for (Object aDataCell : allCells) {
							if (aDataCell instanceof org.jdom.Element) {
								org.jdom.Element aDataCellElem = (org.jdom.Element) aDataCell;
								String text = aDataCellElem.getText();
								Object value = text;
								try {
									value = Integer.parseInt(text);
								} catch (NumberFormatException e) {
									// nothing. Its not integer. try if double
									try {
										value = Double.parseDouble(text);
									} catch (NumberFormatException ex) {
										// nothing. Its not double
									}
								}
								createCell(createRow, colCount, value, cellType);
								
							
							}
							colCount ++;
						}
					}
					rowCount++;
				}
			}
		}

		Row row = workSheet.getRow(0);
		short lastCellNum = row.getLastCellNum();
		for(short count = 0; count < lastCellNum; count ++) {
			workSheet.autoSizeColumn(count);
		}
	}

	@SuppressWarnings("rawtypes")
	private void creatHeaderCell(org.jdom.Document build, Sheet createSheet) {
		org.jdom.Element rootElement = build.getRootElement();
		org.jdom.Element child = rootElement.getChild("headers");
		if(null != child) {
			Row createRow = createSheet.createRow(0);
			List children = child.getChildren("header");
			if(!CollectionUtils.isEmpty(children)) {
				int i=0;
				for (Object object : children) {
					if (object instanceof org.jdom.Element) {
						org.jdom.Element element = (org.jdom.Element) object;

						String text = element.getText();
						createCell(createRow, i, text, HEADER_ROW);
					}
					i++;
				}
			}
		}

	}

	
}
