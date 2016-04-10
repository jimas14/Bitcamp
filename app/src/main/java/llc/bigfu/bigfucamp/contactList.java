package llc.bigfu.bigfucamp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by phil on 4/8/16.
 *
 */
public class contactList extends BaseAdapter {

// Dimitri changed allTasks from hashmap to arraylist
// because we dont need to worry about priority values
    private ArrayList<Person> allTasks;
    private final Context mContext;

    public contactList (Context c) {
        mContext = c;
        allTasks = new ArrayList<Person>();
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }


    //Method Updated 4:40 pm saturday BB
    public boolean add(Person p) {
        if(p == null){
           return false;
        }
        else {
            //make sure phone number doesn't already exist in the allTasks arraylist
            for (Person temp: allTasks) {
                if(temp.phoneNumber.equalsIgnoreCase(p.phoneNumber)) {
                   return false;
                }
            }
            allTasks.add(p);
            notifyDataSetChanged();
            return true;
        }

    }

    public void clear() {

        allTasks.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return allTasks.size();

    }

    // Retrieve the number of ToDoItems

    @Override
    public Object getItem(int pos) {
        return allTasks.get(pos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Person p = (Person)this.getItem(position);

        RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.single_task, parent, false);

        TextView name = (TextView)itemLayout.findViewById(R.id.name);
        name.setText(p.name);

        TextView interval = (TextView)itemLayout.findViewById(R.id.interval);
        interval.setText(p.getIntervalString());

        //TODO
        //TODO
        //TODO
        //TODO
        //TODO
        //SET IMAGE
        ImageView image = (ImageView)itemLayout.findViewById(R.id.pic);

        return itemLayout;

    }
}
