package BaseClasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetDate {

    public static String dateToday(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateNow = localDate.format(formatter);
        return dateNow;
    }

    public static String dateTomorrow(){
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String tomorrow = newDate.format(formatter);
        return tomorrow;
    }
    public static String dateYesterday(){
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String yesterday = newDate.format(formatter);
        return yesterday;
    }

    public static String dayPlusRandom(Integer days){
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.minusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String yesterday = newDate.format(formatter);
        return yesterday;
    }
}
