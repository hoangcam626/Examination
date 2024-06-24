package com.university.examination.util.excel;

import com.university.examination.dto.userinfo.sdo.UserInfoShortSelfSdo;
import com.university.examination.exception.CustomException;
import com.university.examination.util.constant.Gender;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"STT", "Họ tên", "Ngày sinh", "Số CCCD", "Giới tính", "Chú thích"};
    static String SHEET = "Dang ky";

    public static ByteArrayInputStream infoToExcel(List<UserInfoShortSelfSdo> req) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {

            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }


            int rowIdx = 1;
            for (UserInfoShortSelfSdo item : req) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(rowIdx - 1);
                row.createCell(1).setCellValue(item.getFullName());
                row.createCell(2).setCellValue(item.getDateOfBirth());
                row.createCell(3).setCellValue(item.getIdentifyNo());
                row.createCell(4).setCellValue(Gender.values()[item.getGender()].toString());
                row.createCell(5).setCellValue("");
            }
            for (int col = 0; col < HEADERs.length; col++) {
                sheet.autoSizeColumn(col);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new CustomException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
