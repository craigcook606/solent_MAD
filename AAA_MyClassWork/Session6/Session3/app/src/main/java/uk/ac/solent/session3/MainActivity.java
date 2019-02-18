package uk.ac.solent.session3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.preference.PreferenceManager;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    public static final Double DEFAULT_LAT = 50.9246;
    public static final Double DEFAULT_LON = -1.3719;
    public static final Integer DEFAULT_ZOOM = 11;

    MapView mv;
    TextView mi;
    TextView mo;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        EditText my = (EditText) findViewById(R.id.etLat);
        my.setText(DEFAULT_LAT.toString());
        EditText mt = (EditText) findViewById(R.id.etLon);
        mt.setText(DEFAULT_LON.toString());

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        mv = (MapView) findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(DEFAULT_ZOOM);
        mv.getController().setCenter(new GeoPoint(DEFAULT_LAT, DEFAULT_LON));

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.choosemap)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        if(item.getItemId() == R.id.tbd)
        {
            // react to the menu item being selected...
            System.out.println("DEBUG MESSAGE called tbd");


            return true;
        }
        if(item.getItemId() == R.id.example1)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,ExampleListActivity1.class);
            startActivityForResult(intent, 0);
            return true;
        }
        if(item.getItemId() == R.id.example2)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,ExampleListActivity2.class);
            startActivityForResult(intent, 0);
            return true;
        }

        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {
        if(requestCode==0)
        {
            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean hikebikemap = extras.getBoolean("com.example.hikebikemap");
                if(hikebikemap==true)
                {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        EditText etLon = (EditText) findViewById(R.id.etLon);
        EditText etLat = (EditText) findViewById(R.id.etLat);
        double lat= parseLat(etLat);
        double lon= parseLong(etLon);

        switch (view.getId()) {
            case R.id.btn1: // ok - just continue
                break;
            case R.id.btn2: // reset default
                etLon.setText(DEFAULT_LON.toString());
                etLat.setText(DEFAULT_LAT.toString());
                mv.getController().setZoom(DEFAULT_ZOOM);
                break;
            default:
                break;
        }

        mv = (MapView) findViewById(R.id.map1);
        mv.getController().setCenter(new GeoPoint(lat, lon));
    }


    // lat +90 to -90
    private Double parseLat(EditText geoEditText) {
        String input = geoEditText.getText().toString();
        try {

            Double latitude = Double.parseDouble(input);
            if (latitude > 90 || latitude < -90) {
                geoEditText.setText("");
                geoEditText.setHint("invalid latitude: " + input);
                String message = "invalid latitude";
                popupMessage(message);
                return null;
            }
            return latitude;
        } catch (Exception e) {
            geoEditText.setText("");
            geoEditText.setHint("invalid latitude: " + input);
            String message = "invalid latitude: " + input;
            popupMessage(message);
            return null;
        }
    }

    private void popupMessage(String message) {
        new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(message).show();
    }

    //  long +180 to -180
    private Double parseLong(EditText geoEditText) {
        String input = geoEditText.getText().toString();
        try {
            Double longitude = Double.parseDouble(input);
            if (longitude > 180 || longitude < -180) {
                geoEditText.setText("");
                geoEditText.setHint("invalid longitude: " + input);
                String message = "invalid logitude: " + input;
                popupMessage(message);
                return null;
            }
            return longitude;
        } catch (Exception e) {
            geoEditText.setText("");
            geoEditText.setHint("invalid longitude: " + input);
            String message = "invalid longitude: " + input;
            popupMessage(message);
            return null;
        }
    }







}