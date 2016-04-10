package llc.bigfu.bigfucamp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
/**
 * Created by Dimitri on 4/9/16.
 *
 */

public class RandomTimeGenerator {
    private Date dMin = null;
    private Date dMax = null;

    /**
     * Creates a new instance of RandomTimeGenerator, which will be used in the class
     * that accepts the time intervals from the user, then uses this object to create a random time
     * between those two points in time
     */
    public RandomTimeGenerator(Date min, Date max) {
        dMin = min;
        dMax = max;
    }

    public Date generate() {
        long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;
        GregorianCalendar s = new GregorianCalendar();
        s.setTimeInMillis(dMin.getTime());
        GregorianCalendar e = new GregorianCalendar();
        e.setTimeInMillis(dMax.getTime());

        // Get difference in milliseconds
        long endL = e.getTimeInMillis() + e.getTimeZone().getOffset(e.getTimeInMillis());
        long startL = s.getTimeInMillis() + s.getTimeZone().getOffset(s.getTimeInMillis());
        long dayDiff = (endL - startL) / MILLIS_PER_DAY;

        Calendar cal = Calendar.getInstance();
        cal.setTime(dMin);
        cal.add(Calendar.DATE, new Random().nextInt((int) dayDiff));
        return cal.getTime();
    }
}
