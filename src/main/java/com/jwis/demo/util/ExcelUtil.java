package com.jwis.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.*;
@Slf4j
public class ExcelUtil {
    public static List<Map<String,String>> listpart = new ArrayList<>();

    public static Workbook judgeExcel(InputStream in, String filename) throws IOException {
        Workbook workbook = null;
        if (filename.endsWith(".xls")) {
            workbook = new HSSFWorkbook(in);
        } else if (filename.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(in);
        }
        return workbook;
    }




    //读取excel，暂时只读取一个sheet
    public static List<Map> readExcel(FileInputStream in, String filename) throws IOException {
        Workbook workbook = judgeExcel(in, filename);
//        int num = workbook.getNumberOfSheets();
//        for (int i = 0; i < num; i++) {
//            Sheet sheet = workbook.getSheetAt(i);
//            Map<String, String> map = readSheet(sheet);
//            listpart.add(map);
//        }
        Sheet sheet = workbook.getSheetAt(0);
        List<Map> map = readSheet(sheet);
        Map readtitle = readtitle(sheet);
        map.add(0,readtitle);
        return map;
    }

//读取sheet第一行中文
    public static Map readtitle(Sheet sheet){
        Map titlemap = new HashMap();
        String[] title_cn = readRow(sheet.getRow(0));
        String[] title = readRow(sheet.getRow(1));
        titlemap = new HashMap();
        for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

            titlemap.put(title[j],title_cn[j]);
        }
        return titlemap;
    }
//    读取sheet数据
    public static List<Map> readSheet(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        log.info("lastRowNum:"+lastRowNum);
        List<Map> mapList= new ArrayList<>();
        Map map ;
//        Map titlemap;
//        String[] title_cn = readRow(sheet.getRow(0));
        String[] title = readRow(sheet.getRow(1));
//        for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
//            titlemap = new HashMap();
//            titlemap.put(title[j],title_cn[j]);
//        }
        for (int i = 2; i <= lastRowNum; i++) {
            map = new HashMap();
            String[] value = readRow(sheet.getRow(i));
            System.out.println("value:"+value[0]);
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                map.put(title[j],value[j]);
            }
            mapList.add(map);
        }

        return mapList;
    }

    //读取row
    public static String[] readRow(Row row) {
        short lastCellNum = row.getLastCellNum();
        String[] strings = new String[lastCellNum];
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = row.getCell(i);
            if (cell.getCellType().equals(NUMERIC)) {
                strings[i] = String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue());
            }
            if (cell.getCellType().equals(STRING)) {
                strings[i] = cell.getStringCellValue();
            }
            if (cell.getCellType().equals(BOOLEAN)) {
                strings[i] = String.valueOf(cell.getBooleanCellValue());
            }
            if (cell.getCellType().equals(ERROR)) {
                strings[i] = String.valueOf(cell.getErrorCellValue());
            }
        }


        return strings;
    }

}
