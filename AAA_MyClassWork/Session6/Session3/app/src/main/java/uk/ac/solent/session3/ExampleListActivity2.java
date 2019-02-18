package uk.ac.solent.session3;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Context;

public class ExampleListActivity2 extends android.app.ListActivity {
    String[] places, details;

    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        places = new String[] {"The Craaan", "Moon and Stars", "The Worlds Inn"};
        details = new String[]{"pub, 5 miles south", "pub, 4 miles north", "pub, 3 miles west"};
        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id) {
        // handle list item selection

        // this ceates a simpe pop up as an example action
        String message = " you selected " + places[index];
        popupMessage(message);
    }


    private void popupMessage(String message) {
        new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(message).show();
    }

    public class MyAdapter extends ArrayAdapter<String>
    {
        public MyAdapter()
        {
            super(ExampleListActivity2.this, android.R.layout.simple_list_item_1, places);
        }
        public View getView(int index, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.activity_exlist, parent, false);
            TextView title = (TextView)view.findViewById(R.id.places), detail =
                    (TextView)view.findViewById(R.id.details);
            title.setText(places[index]);
            detail.setText(details[index]);
            return view;
        }

    }
}
