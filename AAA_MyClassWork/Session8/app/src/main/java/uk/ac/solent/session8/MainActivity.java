package uk.ac.solent.session8;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class MainActivity extends AppCompatActivity {

    public static final String DEFAULT_FILENAME = "textedit.txt";
    private String fileName = DEFAULT_FILENAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et = (EditText) findViewById(R.id.e_t);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        EditText et = (EditText) findViewById(R.id.e_t);
        if (item.getItemId() == R.id.save) {
            // react to the menu item being selected...
            String text = et.getText().toString();
            save(text);
            return true;
        }

        if (item.getItemId() == R.id.load) {
            // react to the menu item being selected...
            String text = load();
            et.setText(text);
            return true;
        }

        return false;
    }


    public void onClick(View view) {

    }


    public String load() {
        String s = "";
        BufferedReader reader = null;
        try {
            FileReader fr = new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() + "/edittext.txt");
            reader = new BufferedReader(fr);
            String line = "";
            while ((line = reader.readLine()) != null) {
                // do something with each line...
                s = s + line;
            }

        } catch (IOException e) {
            new AlertDialog.Builder(this).setPositiveButton("OK", null).
                    setMessage("ERROR: " + e).show();

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return s;
    }

    public void save(String text) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/edittext.txt"));

            pw.println(text);

        } catch (IOException e) {
            new AlertDialog.Builder(this).setPositiveButton("OK", null).
                    setMessage("ERROR: " + e).show();
        } finally {
            if (pw != null) pw.close(); // close the file to ensure data is flushed to file
        }
    }
}