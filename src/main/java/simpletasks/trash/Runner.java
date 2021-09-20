package simpletasks.trash;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Runner {
    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar();
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar);
        System.out.println(calendar.getTime().toString());
    }
}
