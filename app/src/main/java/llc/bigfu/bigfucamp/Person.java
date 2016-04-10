package llc.bigfu.bigfucamp;

import android.content.Intent;

import java.util.Date;

/**
 * Created by phil on 4/8/16.
 * Holds all data for the person that is store in the list
 */

public class Person {
    public String NAME;
    public String PHONE_NUMBER;
    public String START;
    public String END;

    public Person(String phoneNumber, String name) {
        this.NAME = name;
        this.PHONE_NUMBER = phoneNumber;
    }

    public Person(String phoneNumber, String name, String start, String end) {
        this.NAME = name;
        this.PHONE_NUMBER = phoneNumber;
        this.START = start;
        this.END = end;
    }

    public String getIntervalString(){
        return ("Start Time: " + this.START.toString() + ". End time: " + this.END.toString());
    }

    @Override
    public String toString(){
        return NAME + "\n" + PHONE_NUMBER;
    }
}
