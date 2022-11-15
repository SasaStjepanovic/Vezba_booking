package excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelSupport {

    String prefixPath = "src/test/test_data/";

    public Map<String, String> getExcelByRow(String fileName, String sheetName, String rowNum) throws IOException {

        FileInputStream fis = new FileInputStream(prefixPath +fileName+ ".xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int row = Integer.parseInt(rowNum);

        int lastColumnNum = sheet.getRow(2).getLastCellNum();

        Map<String, String> data = new HashMap<>();

        for(int i = 0; i < lastColumnNum; i++){
            data.put(
                    sheet.getRow(2).getCell(i).getStringCellValue(),
                    sheet.getRow((row+2)).getCell(i).getStringCellValue()
            );
        }
        return data;
    }

    public Map<String, String> getDataByID(String fileName, String sheetName, String testID) throws Exception {

        FileInputStream fis = new FileInputStream(prefixPath + fileName + ".xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int lastColumnNum = sheet.getRow(2).getLastCellNum();
        int lastRowNum = sheet.getLastRowNum();

        Map<String, String> data = new HashMap<>();
        int match = 0;

        for (int rowIndex = 2; rowIndex <= lastRowNum; rowIndex++) {
            if (sheet.getRow(rowIndex).getCell(0).getStringCellValue().equalsIgnoreCase(testID)) {
                if(match > 1){
                    throw new Exception("Multiple Test Case ID's found for Test Case ID: "+testID);
                }
                for (int columnIndex = 0; columnIndex < lastColumnNum; columnIndex++) {
                    data.put(
                            sheet.getRow(2).getCell(columnIndex).getStringCellValue(),
                            sheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue()
                    );
                }
                match++;
            }
        }

        return data;
    }
}
