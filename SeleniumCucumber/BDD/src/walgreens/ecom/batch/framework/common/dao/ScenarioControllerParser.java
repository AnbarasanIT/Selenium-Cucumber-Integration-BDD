package walgreens.ecom.batch.framework.common.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import walgreens.ecom.batch.framework.common.beans.ScenarioContollerData;
import walgreens.ecom.batch.framework.common.beans.ScenarioControllerRowData;


public class ScenarioControllerParser{

	public static ScenarioControllerRowData readScenarioValue(String fileName,String sheetName,String rowID) throws IOException{
		Iterator<Row> rowIterator = null;
		List<ScenarioContollerData> scenarioControlledList = new ArrayList<ScenarioContollerData>();
		ScenarioControllerRowData scenarioControllerRowData = new ScenarioControllerRowData();
		FileInputStream fileInputStream = null;
		File filename = new File(fileName);
		fileInputStream = new FileInputStream(filename);
		HSSFWorkbook objWorkbook = new HSSFWorkbook (fileInputStream);
		HSSFSheet objSheet = objWorkbook.getSheet(sheetName);
		rowIterator = objSheet.rowIterator();

		for (int i = 0; i <1; i++) {
			rowIterator.next();
		}

		ScenarioContollerData scenariocontrollerData = new ScenarioContollerData();
		while (rowIterator.hasNext()) {
			Row next = rowIterator.next();
			scenariocontrollerData = createObject(next);
			scenarioControlledList.add(scenariocontrollerData);
		}

		for (ScenarioContollerData scenarioContollerDataList : scenarioControlledList) {
			String id = scenarioContollerDataList.getId();
			if(id.equalsIgnoreCase(rowID)) {
				scenarioControllerRowData.setId(scenarioContollerDataList.getId());
				scenarioControllerRowData.setScenarioNumber(scenarioContollerDataList.getScenarioNumber());
				scenarioControllerRowData.setEnableExecution(scenarioContollerDataList.getEnableExecution());
				scenarioControllerRowData.setRunforProduction(scenarioContollerDataList.getRunforProduction());
				scenarioControllerRowData.setOpenBrowser(scenarioContollerDataList.getOpenBrowser());
				scenarioControllerRowData.setClearBrowserCache(scenarioContollerDataList.getClearBrowserCache());
				scenarioControllerRowData.setCucumberReportEnabled(scenarioContollerDataList.getCucumberReportEnabled());
				scenarioControllerRowData.setExcelReportEnabled(scenarioContollerDataList.getExcelReportEnabled());
				scenarioControllerRowData.setaLMReportEnabled(scenarioContollerDataList.getaLMReportEnabled());
				scenarioControllerRowData.setPriority(scenarioContollerDataList.getPriority());
				scenarioControllerRowData.setScenarioDescription(scenarioContollerDataList.getScenarioDescription());
				scenarioControllerRowData.setScenarioFeatureName(scenarioContollerDataList.getScenarioFeatureName());
			}
		}
		return scenarioControllerRowData;
	}

	private static ScenarioContollerData createObject(Row next) {
		ScenarioContollerData smoke = new ScenarioContollerData();

		if(next.getCell(0) != null) {
			Cell cell = next.getCell(0);
			String value = getValue(cell);
			if(value != null) {
				smoke.setId(value);
			}
		}
		if(next.getCell(1) != null) {
			Cell cell = next.getCell(1);
			String value = getValue(cell);
			if(value != null) {
				smoke.setScenarioNumber(value);
			}
		}
		if(next.getCell(2) != null) {
			Cell cell = next.getCell(2);
			String value = getValue(cell);
			if(value != null) {
				smoke.setEnableExecution(value);
			}
		}
		if(next.getCell(3) != null) {
			Cell cell = next.getCell(3);
			String value = getValue(cell);
			if(value != null) {
				smoke.setRunforProduction(value);
			}
		}
		if(next.getCell(4) != null) {
			Cell cell = next.getCell(4);
			String value = getValue(cell);
			if(value != null) {
				smoke.setOpenBrowser(value);
			}
		}
		if(next.getCell(5) != null) {
			Cell cell = next.getCell(5);
			String value = getValue(cell);
			if(value != null) {
				smoke.setClearBrowserCache(value);
			}
		}
		if(next.getCell(6) != null) {
			Cell cell = next.getCell(6);
			String value = getValue(cell);
			if(value != null) {
				smoke.setCucumberReportEnabled(value);
			}
		}
		if(next.getCell(7) != null) {
			Cell cell = next.getCell(7);
			String value = getValue(cell);
			if(value != null) {
				smoke.setExcelReportEnabled(value);
			}
		}
		if(next.getCell(8) != null) {
			Cell cell = next.getCell(8);
			String value = getValue(cell);
			if(value != null) {
				smoke.setaLMReportEnabled(value);
			}
		}
		if(next.getCell(9) != null) {
			Cell cell = next.getCell(9);
			String value = getValue(cell);
			if(value != null) {
				smoke.setPriority(value);
			}
		}
		if(next.getCell(10) != null) {
			Cell cell = next.getCell(10);
			String value = getValue(cell);
			if(value != null) {
				smoke.setScenarioDescription(value);
			}
		}
		if(next.getCell(11) != null) {
			Cell cell = next.getCell(11);
			String value = getValue(cell);
			if(value != null) {
				smoke.setScenarioFeatureName(value);
			}
		}
		return smoke;
	}

	private static String getValue(Cell cell) {
		if (cell != null) {
			if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
				return cell.getStringCellValue();
			}

			if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
				return String.valueOf(cell.getNumericCellValue());
			}
		}

		return null;
	}
}

