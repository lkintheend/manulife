package com.hitex.menulife.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Util {

    public static String convertStringToTimestamp(String str_date) {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            // you can change format of date
            Date date = formatter.parse(str_date);

            return String.valueOf(date.getTime());
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return "";
        }
    }

    public static String convertTimestampToString(String timeStamp) throws ParseException {
        DateFormat formatter;
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date();
        formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        date = formatter.parse(date.toString());
        return date.toString();

    }

    public static String convertDate(String dateInput) throws ParseException {
        DateFormat formatter1, formatter2;
        Date date = new Date();
        formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        date = formatter1.parse(dateInput);
        return formatter2.format(date);
    }

    public static String RandomPassword() {
        Random random = new Random();
        return String.valueOf(1000 + random.nextInt(8999));
    }
    
    public static String getExtendsFileName(String fileName){
        if(fileName.contains(".")&& (fileName.lastIndexOf(".")!=0)){
            return fileName.substring(fileName.lastIndexOf(".")+1);
        } else {
            return "";
        }
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(convertStringToTimestamp("2018-06-06 14:43:08.0"));
    }
}

//import java.util.*;1528688704000
//import java.text.*;
//import java.sql.Timestamp;
//
//public class DateToTimestamp {
//
//    public static void main(String[] args) {
//        try {
//            String str_date = "11-June-07";
//            DateFormat formatter;
//            Date date;
//            formatter = new SimpleDateFormat("dd-MMM-yy");
//            date = (Date) formatter.parse(str_date);
//            System.out.println(date.getTime());
//        } catch (ParseException e) {
//            System.out.println("Exception :" + e);
//        }
//
//    }
//}
