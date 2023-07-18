package org.example;

import org.apache.tika.utils.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {

     //   foo();
        //time();
        time1();
    }

    static void foo() {
        String m = "Hello";
        System.out.print(m);
        bar(m);
        System.out.print(m);
    }

    static void time() throws ParseException {
        Date date = new Date();
        Date date1 = new Date();



        DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");

        String today = formatter1.format(date);


        String tomorrow = formatter1.format(date1);

        System.out.println("Today : " + today);
        System.out.println("Today +1: " + tomorrow);



    }

    static void time1() {
        LocalDate localDate = LocalDate.now();
        System.out.println("Current Date: "+localDate);
        // Add Weeks
        LocalDate newLocalDate = localDate.plusDays(1);
    //    System.out.println("Date After incrementing a Day: "+newLocalDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedString = newLocalDate.format(formatter);

        System.out.println(formattedString);
    }

    static void bar(String m) {
        m += " World!";
    }
}