package com.xiaozhuge.springbootldap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;

public class TestOperationExcel {
 
    private static String fileName = "/Users/liyinlong/alipay.xlsx";
 
    public static void main(String[] args) throws Exception{
//        createExcel();
        readExcel();
    }
 
    public static void createExcel() throws Exception {
        //创建一个excel文件，名称为：
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建一个sheet，名称为工作簿1
        XSSFSheet sheet = workbook.createSheet("工作簿1");
        XSSFRow titleRow = sheet.createRow(0);
 
        XSSFCell nameCell = titleRow.createCell(0);
        nameCell.setCellValue("小诸葛的博客");
 
        XSSFCell idCell = titleRow.createCell(1);
        idCell.setCellValue("gdupa2015");
 
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        workbook.write(fileOutputStream);
    }
 
    public static void readExcel() throws Exception {
        Connection dbConn = null;
        String DBDRIVER = "com.mysql.jdbc.Driver";//mysql数据库驱动类
        String DBUSER = "root";//数据库用户名
        String DBPASS = "Hc@Cloud01";//数据库密码
        String DBURL = "jdbc:mysql://localhost:3309/kuafu_dev?characterEncoding=UTF-8";
        try{
            Class.forName(DBDRIVER);
            dbConn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            System.out.println("数据库连接成功");
        }catch(Exception e){
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }

        //1.获取excel文件
        XSSFWorkbook workbook = new XSSFWorkbook(fileName);
        //2.获取第一个工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        rowIterator.next();
        int i = 0;
        while (rowIterator.hasNext()){
            i++;
            if(i > 1290){
                return;
            }
            String sql = "insert into record (`category`,`username`,`account`,`usetype`,`from`,`num`,`amount`,`result`," +
                    "`orderid`,`orderid2`,`time`) values (?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = dbConn.prepareStatement(sql);
            Row next = rowIterator.next();
            String category = next.getCell(0).getStringCellValue();
            String username = next.getCell(1).getStringCellValue();
            String account = next.getCell(2).getStringCellValue();
            String usetype = null;
            try {
                usetype = next.getCell(3).getStringCellValue();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(i);
                usetype = "无";
            }
            String from = next.getCell(4).getStringCellValue();
            double numericCellValue = next.getCell(5).getNumericCellValue();
            String num = String.valueOf((int) numericCellValue);
            String amount = next.getCell(6).getStringCellValue();
            String result = next.getCell(7).getStringCellValue();
            String orderid = next.getCell(8).getStringCellValue();
            String orderid2 = next.getCell(9).getStringCellValue();
            Date date = next.getCell(10).getDateCellValue();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String time = String.valueOf(format.format(date));
            ps.setString(1, category);
            ps.setString(2, username);
            ps.setString(3, account);
            ps.setString(4, usetype);
            ps.setString(5, from);
            ps.setInt(6, Integer.parseInt(num));
            ps.setString(7, amount);
            ps.setString(8,result);
            ps.setString(9, orderid);
            ps.setString(10, orderid2);
            ps.setString(11, time);
            ps.execute();
        }
        //3.获取工作表的第一行
//        XSSFRow row1 = sheet.getRow(0);
        //4.获取第一行的第一列、第二列单元格
//        XSSFCell cell1 = row1.getCell(0);
//        XSSFCell cell2 = row1.getCell(1);
        //5.以字符串方式返回第一列单元格的内容
//        String cell1Value = cell1.getStringCellValue();
//        System.out.println("name=>" + cell1Value);

 
    }
}