package llc.bigfu.bigfucamp;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class EnterActivity extends Activity {
    static final int PICK_CONTACT = 1;
    private Person current;

    static TextView start, end;
    static Date start_t,end_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button contact = (Button)findViewById(R.id.selectContact);

        contact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

        Button okay = (Button)findViewById(R.id.okay);

        okay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent();
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

        end = (TextView)findViewById(R.id.end_time);
        start = (TextView)findViewById(R.id.start_time);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                showTimePickerDialog(0);

            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                showTimePickerDialog(1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CONTACT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Uri contactData = data.getData();

                

                //current.packageIntent(data,phone,name,start,end);


                // Do something with the contact here (bigger example below)

                /*
                Cursor c = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, null, null, null);
                if (c.moveToFirst())
                {
                    String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                    String hasPhone =
                            c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                    if (hasPhone.equalsIgnoreCase("1"))
                    {
                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                        phones.moveToFirst();
                        String cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        // Toast.makeText(getApplicationContext(), cNumber, Toast.LENGTH_SHORT).show();

                    }
                }
                */
            }
        }
    }
    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        private int x;
        public TimePickerFragment(int x) {
            super();
            this.x = x;

        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
           // setTimeString(hourOfDay, minute, 0);
            if (this.x == 1) {
                final Calendar c = Calendar.getInstance();
                end.setText(hourOfDay+":"+minute);
                end_t = new Date(c.get(Calendar.YEAR), (c.get(Calendar.MONTH)),(c.get(Calendar.DATE)), hourOfDay, minute);

            } else {
                final Calendar c = Calendar.getInstance();
                start_t = new Date(c.get(Calendar.YEAR), (c.get(Calendar.MONTH)),(c.get(Calendar.DATE)), hourOfDay, minute);
                start.setText(hourOfDay + ":" + minute);

            }
            //timeView.setText(timeString);

        }
    }

    private void showTimePickerDialog(int x) {
        DialogFragment newFragment = new TimePickerFragment(x);
        newFragment.show(getFragmentManager(), "timePicker");
    }
}