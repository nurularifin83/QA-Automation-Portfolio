package com.orangehrm.testdatareaders;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    private String filePath;

    public ExcelReader(String filePath){
        this.filePath = filePath;
    }

    public List<String> getFieldNamesFromExcel(String sheetName) throws IOException {
        List<String> fieldNames = new ArrayList<>();
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            int rows = sheet.getPhysicalNumberOfRows();
            for (int row = 1;row < rows; row++){
                fieldNames.add(sheet.getRow(row).getCell(0).getStringCellValue());
            }
            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            System.err.println("IOException occurred while reading the Excel file: " +e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return fieldNames;
    }
}