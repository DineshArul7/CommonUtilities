package org.example.usage;

import org.example.utility.excelreading.ExcelReader;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class ExcelUsage {

    ExcelReader read=new ExcelReader();
@Test
    public void testCase() throws IOException {
    for(HashMap<String,String> excel:read.excelread()){
         System.out.println(excel.get("Year"));
         System.out.println(excel.get("Test Case Name"));
    }
}
}
