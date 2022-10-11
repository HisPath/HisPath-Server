package com.server.hispath.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.hispath.activity.application.dto.MActivityContentDto;
import com.server.hispath.department.application.dto.DepartmentDto;
import com.server.hispath.exception.common.ExcelDataFormatException;
import com.server.hispath.exception.common.ExcelFormatException;
import com.server.hispath.exception.common.NotExcelExtensionException;
import com.server.hispath.major.application.dto.MajorDto;
import com.server.hispath.student.application.dto.StudentRefDto;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

    public static Sheet extract(MultipartFile file) throws Exception {
        Workbook workbook = null;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new NotExcelExtensionException();
        }

        return workbook.cloneSheet(0);
    }

    public static void validate(String semester) {
        String pattern = "[0-9]{4}-[0-9]";
        if (!Pattern.matches(pattern, semester))
            throw new ExcelDataFormatException(semester);
    }

    public static List<MActivityContentDto> getMActivities(Sheet worksheet) {
        List<MActivityContentDto> mActivityContentDtos = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            try {
                Row row = worksheet.getRow(i);
                Long categoryId = Long.parseLong(row.getCell(0).toString().split("\\.")[0].toString());
                String activityName = row.getCell(1).toString();
                String remark = row.getCell(2).toString();

                LocalDateTime startDate = LocalDateTime.parse(row.getCell(3).toString(), dateTimeFormatter);
                LocalDateTime endDate = LocalDateTime.parse(row.getCell(4).toString(), dateTimeFormatter);
                int weight = Integer.parseInt(row.getCell(5).toString());
                String semester = row.getCell(6).toString();
                validate(semester);
                mActivityContentDtos.add(new MActivityContentDto(categoryId, semester, activityName, remark, weight, startDate, endDate));
            } catch (Exception e) {
                throw new ExcelFormatException(e.getMessage());
            }

        }
        return mActivityContentDtos;
    }

    public static List<StudentRefDto> getStudentDatas(Sheet worksheet) {
        List<StudentRefDto> studentRefDtos = new ArrayList<>();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            try {
                Row row = worksheet.getRow(i);
                String studentName = row.getCell(0).toString();
                String str = row.getCell(1).toString();
                int num = new BigDecimal(str).intValue();
                String studentNum = Integer.toString(num);
                int studentSemester = Integer.parseInt(row.getCell(2).toString().split("\\.")[0]);
                Long departmentId = Long.parseLong(row.getCell(3).toString().split("\\.")[0]);//                int departmentId = 0;
                Long major1Id = Long.parseLong(row.getCell(4).toString().split("\\.")[0]);
                Long major2Id = Long.parseLong(row.getCell(5).toString().split("\\.")[0]);

                studentRefDtos.add(new StudentRefDto((long) i, studentName, studentNum,
                        studentSemester, departmentId, major1Id, major2Id, "010-1234-1234", "handong@ac.kr",
                        "url", "blog.com", "@github", "readme content"));

            } catch (Exception e) {
                throw new ExcelFormatException(e.getMessage());
            }
        }
        return studentRefDtos;
    }
}
