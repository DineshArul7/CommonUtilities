package org.example.utility.excelreading;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    String userdir=System.getProperty("user.dir");
    String path =userdir+"\\src\\main\\java\\org\\example\\resources\\annual-enterprise-survey-2020-financial-year-provisional-csv.xlsx";
    File file;
    FileInputStream fis;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Iterator<Row> rowIterator;
    Iterator<Cell> cellIterator;
    Row row;
    Cell cell;
    ArrayList<String> firstrowvalues=new ArrayList<String>();
    ArrayList<HashMap<String,String>> completedata=new ArrayList<HashMap<String, String>>();



    public ArrayList<HashMap<String,String>> excelread() throws IOException {
        file=new File(path);
        fis=new FileInputStream(file);
        workbook=new XSSFWorkbook(fis);
        sheet=workbook.getSheetAt(0);
        rowIterator=sheet.iterator();
        row=rowIterator.next();
        cellIterator=row.iterator();
        getfirstrowvalues();
        getallthevalues();
workbook.close();

return completedata;
    }

    public List<String> getfirstrowvalues(){

        while (cellIterator.hasNext()){
            firstrowvalues.add(cellIterator.next().toString());
        }
        return firstrowvalues;
    }
    public void getallthevalues() {

        while (rowIterator.hasNext()) {
            HashMap<String,String> data=new HashMap<String,String>();
            row=rowIterator.next();
            cellIterator=row.iterator();
            String testName = cellIterator.next().toString();
           // System.out.println(testName);
            String flag = cellIterator.next().toString();
           // System.out.println(flag);

            if (flag.equalsIgnoreCase("yes")) {
                data.put(firstrowvalues.get(0), testName);
                for (int i = 2; i < firstrowvalues.size(); i++) {
                    data.put(firstrowvalues.get(i), cellIterator.next().toString());
                }
               completedata.add(data);
            }
        }
    }

}
