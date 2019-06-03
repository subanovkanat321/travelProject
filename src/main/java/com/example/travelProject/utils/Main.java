package com.example.travelProject.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        Calendar t2 = new GregorianCalendar(2016, 9, 26);
        Calendar t1 = new GregorianCalendar(2016, 8, 13);
//        System.out.println(getDifference(t2, t1));
        Integer integer = Integer.valueOf(getDifference(t2, t1));
        System.out.println(integer);
    }

    private static String getDifference(Calendar t1, Calendar t2) {
        StringBuilder result = new StringBuilder();
//        result.append(t1.get(Calendar.YEAR) > t2.get(Calendar.YEAR) ? t1.get(Calendar.YEAR) - t2.get(Calendar.YEAR)
//                : t2.get(Calendar.YEAR) - t1.get(Calendar.YEAR));
//        result.append(":");
//        result.append(t1.get(Calendar.MONTH) > t2.get(Calendar.MONTH) ? t1.get(Calendar.MONTH) - t2.get(Calendar.MONTH)
//                : t2.get(Calendar.MONTH) - t1.get(Calendar.MONTH));
//        result.append(":");
        result.append(t1.get(Calendar.DATE) > t2.get(Calendar.DATE) ? t1.get(Calendar.DATE) - t2.get(Calendar.DATE)
                : t2.get(Calendar.DATE) - t1.get(Calendar.DATE));
        return result.toString();
    }
    public static void getResult(LocalDate firstDate, LocalDate secondDate) {
        Period period = Period.between(secondDate, firstDate);
        System.out.println(period.getYears() + "." + period.getMonths() + "." + period.getDays());
    }
}
