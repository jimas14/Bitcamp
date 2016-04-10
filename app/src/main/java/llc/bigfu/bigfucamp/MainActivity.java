package llc.bigfu.bigfucamp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final int ADD_NEW = 2;
    private ArrayList<String> ppl;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addNew = (Button)findViewById(R.id.addNew);
        ListView list = (ListView)findViewById(R.id.contactList);

        ppl = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ppl);
        arrayAdapter.notifyDataSetChanged();
        list.setAdapter(arrayAdapter);

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, ADD_NEW);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        System.out.println("\n\n\n\n\n RequestCode = " + requestCode);
        if (requestCode == ADD_NEW) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String num = data.getStringExtra("num");

                System.out.println("\n\n\n\n Final Stage Name: " + name + " and num: " + num);

                ppl.add(name + "\n" + num);

                arrayAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
