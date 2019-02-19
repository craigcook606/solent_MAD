package com.example.whitelegg_n.feettometres;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FeetToMetresActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.convertBtn);
        btn.setOnClickListener(this);
    }

    public void onClick(View v) {
        EditText etFeet = (EditText)findViewById(R.id.feet);
        double feet = Double.parseDouble(etFeet.getText().toString());
        double metres = feet * 0.305;
        TextView tvMetres = (TextView)findViewById(R.id.metres);
        tvMetres.setText("Metres: " + metres);
    }
}
