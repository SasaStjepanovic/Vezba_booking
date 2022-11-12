package excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelSupport {

    public Map<String, String> getExcelRow(String fileName, String sheetName, String rowNum) throws IOException {

        FileInputStream fis = new FileInputStream("src/test/test_data/"+fileName+".xlsx");
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
}
