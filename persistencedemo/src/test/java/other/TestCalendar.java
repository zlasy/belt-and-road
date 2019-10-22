package other;

import java.util.Calendar;
import java.util.Date;


public class TestCalendar {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 24 * -1);
        Date date1 = calendar.getTime();
        calendar.add(Calendar.MINUTE, -10);
        Date date2 = calendar.getTime();
        calendar.add(Calendar.MINUTE, 20);
        Date date3 = calendar.getTime();
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        
        
        Date enDate = new Date();
        long end = enDate.getTime();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(enDate);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        if (calendar1.get(Calendar.HOUR_OF_DAY) == 0) {
            calendar1.set(Calendar.HOUR_OF_DAY, 23);
        }
        if (calendar1.get(Calendar.MINUTE) == 0) {
            calendar1.set(Calendar.MINUTE, 59);
        }
        if (calendar1.get(Calendar.SECOND) == 0) {
            calendar1.set(Calendar.SECOND, 59);
        }
        System.out.println("end:" + enDate);
        System.out.println("end:" + calendar1.getTime());
    }

}
