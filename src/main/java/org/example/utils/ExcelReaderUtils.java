package org.example.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReaderUtils {

    public static List<String[]> readExcel(String filePath, String sheetName) throws IOException {
        List<String[]> list = new ArrayList<String[]>();

        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        for(Row row : sheet) {
            int cellCount = row.getLastCellNum();
            String[] rowData = new String[cellCount];

            for(int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
                Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                rowData[cellIndex] = cell.toString();
            }
            list.add(rowData);
        }

        workbook.close();
        file.close();
        return list;
    }

}
