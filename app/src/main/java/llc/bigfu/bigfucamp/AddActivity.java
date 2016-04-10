package llc.bigfu.bigfucamp;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends Activity {
    static final int PICK_CONTACT = 1;
    private Person current;

    static TextView start, end;
    static Date start_t,end_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_screen);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

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
                Intent intent = getIntent();

                System.out.println("\n\n\n\n SemiFinal Stage Name: " + current.NAME + " and num: " + current.PHONE_NUMBER);
                intent.putExtra("name", current.NAME);
                intent.putExtra("num", current.PHONE_NUMBER);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        Button instant = (Button)findViewById(R.id.instant);

        instant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String toPhoneNumber = current.PHONE_NUMBER;
                String smsMessage = RandomText.getRandomText();


                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(toPhoneNumber, null, smsMessage, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Sending SMS failed.",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

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
        System.out.println("\n\n\n\n\n RequestCode = " + requestCode);
        if (requestCode == PICK_CONTACT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Uri contactUri = data.getData();

                // We only need the NUMBER column, because there will be only one row in the result
                String[] num_projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                String[] name_projection = {ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor num_cursor = getContentResolver().query(contactUri, num_projection, null, null, null);
                Cursor name_cursor = getContentResolver().query(contactUri, name_projection, null, null, null);
                num_cursor.moveToFirst();
                name_cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = num_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int column1 = name_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME);
                String number = num_cursor.getString(column);
                String name = name_cursor.getString(column1);

                System.out.println("\n\n\n\n\n Debug Phone Number: " + number);
                System.out.println("\n\n\n\n\n Debug Name: " + name);


                current = new Person(number,name);
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