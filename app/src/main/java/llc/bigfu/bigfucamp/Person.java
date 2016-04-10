package llc.bigfu.bigfucamp;

import android.content.Intent;

import java.util.Date;

/**
 * Created by phil on 4/8/16.
 * Holds all data for the person that is store in the list
 */

public class Person {

    //public final String P_V = "PRIORITY_VALUE";
    public static final String NAME = "NAME_VALUE";
    public static final String START = "START_VALUE";
    public static final String END = "END_VALUE";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";

    public int x, y;
    public Date start, end;
    public String name;
    public String phoneNumber;

    public Person(Intent i ) {
       // pv = i.getIntExtra(P_V, 1);
        name = i.getStringExtra(NAME);
        phoneNumber = i.getStringExtra(PHONE_NUMBER);
        this.start = (Date)i.getSerializableExtra(START);
        this.end = (Date)i.getSerializableExtra(END);
    }

    //updated by BB on 4:45pm saturday
    public Person(String phoneNumber, String n, Date start, Date end) {
        this.name = n;
        this.phoneNumber = phoneNumber;
        this.start = start;
        this.end = end;
    }

    public static void packageIntent(Intent intent, String phoneNumber, String n, Long start, Long end) {
        intent.putExtra(Person.NAME, n);
        intent.putExtra(Person.PHONE_NUMBER, phoneNumber);
        intent.putExtra(Person.START, start);
        intent.putExtra(Person.END, end);
    }


    public String getIntervalString(){
        return ("Start Time: " + this.start.toString() + ". End time: " + this.end.toString());
    }


}
