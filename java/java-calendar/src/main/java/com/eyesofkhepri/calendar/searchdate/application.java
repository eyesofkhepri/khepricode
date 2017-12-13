package com.eyesofkhepri.calendar.searchdate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class application {
    public static void main(String[] args) {
        System.out.println("START");

        // 지난 달
        System.out.println("=== 지난달 ===");
        System.out.println(getPreMonthDate());
        // 전주
        System.out.println("=== 전주 ===");
        System.out.println(getPreWeekDate());
        // 3달 전 - 어제기준
        System.out.println("=== 3달전-어제기준 ===");
        System.out.println(getThreePreMonthDate());

        System.out.println("END");
    }

    private static DateResult getThreePreMonthDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -3);
        cal.add(cal.DATE, -1);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(cal2.DATE, -1);

        return new DateResult(new SimpleDateFormat("yyyyMMdd").format(cal.getTime()), new SimpleDateFormat("yyyyMMdd").format(cal2.getTime()));
    }

    private static DateResult getPreWeekDate() {
        List<String> dayList = new ArrayList();
        Calendar cal = Calendar.getInstance();

        while(cal.get(Calendar.DAY_OF_WEEK) != 1) {
            cal.add(Calendar.DATE, -1);
        }

        cal.add(Calendar.DATE, -1);
        dayList.add(new SimpleDateFormat("yyyyMMdd").format(cal.getTime()));

        while(cal.get(Calendar.DAY_OF_WEEK) != 1) {
            cal.add(Calendar.DATE, -1);
            dayList.add(new SimpleDateFormat("yyyyMMdd").format(cal.getTime()));
        }

        return new DateResult(dayList.get(6), dayList.get(0));
    }

    private static DateResult getPreMonthDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH,-1);
        cal.set(Calendar.DATE, 1);

        int DayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String beforeMonth = dateFormat.format(cal.getTime());

        return new DateResult(beforeMonth.concat("01"), beforeMonth.concat(String.valueOf(DayOfMonth)));
    }
}
