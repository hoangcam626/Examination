package com.university.examination.util.excel;

import com.university.examination.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.university.examination.exception.CustomException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"STT", "Id", "Họ tên", "Ngày sinh", "Số CCCD",  "Chú thích"};
    static String SHEET = "Dang ky";

    public static ByteArrayInputStream tutorialsToExcel(List<UserInfoShortSelfSdo> req) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (UserInfoShortSelfSdo item : req) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowIdx - 1);
                row.createCell(1).setCellValue(item.getId());
                row.createCell(2).setCellValue(item.getFullName());
                row.createCell(3).setCellValue(item.getDateOfBirth());
                row.createCell(4).setCellValue(item.getIdentifyNo());
                row.createCell(5).setCellValue("");
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new CustomException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
