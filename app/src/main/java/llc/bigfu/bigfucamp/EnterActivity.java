package llc.bigfu.bigfucamp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import java.util.Calendar;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Button;

public class EnterActivity extends Activity {
    static final int PICK_CONTACT = 1;
    private Person current;

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
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                //int column1 = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.);
                String number = cursor.getString(column);
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity._ID));
                //String name = cursor.getString(column1);

                System.out.println("\n\n\n\n\n Debug Phone Number: " + number);
                System.out.println("\n\n\n\n\n Debug Name: " + name);

                //current.packageIntent(data,phone,name,start,end);

                current.packageIntent(data,number,null);


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

            //timeView.setText(timeString);
        }
    }

    private void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
}