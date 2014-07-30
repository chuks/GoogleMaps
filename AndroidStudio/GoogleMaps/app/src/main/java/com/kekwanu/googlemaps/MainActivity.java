package com.kekwanu.googlemaps;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends Activity {
    static final LatLng SEATTLE = new LatLng(47.609895,  -122.330259);
    static final LatLng TACOMA  = new LatLng(47.25287, -122.44429);
    private int RQS_GooglePlayServices = 0;

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        Marker seattle = map.addMarker(new MarkerOptions()
                .position(SEATTLE)
                .title("Seattle")
                .snippet("Seattle is sunny")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_launcher)));

        Marker tacoma = map.addMarker(new MarkerOptions()
                .position(TACOMA)
                .title("Tacoma")
                .snippet("Tacoma is sunny")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_launcher)));

        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SEATTLE, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        //this is just to play around with settings...
        UiSettings uisetitngs =  map.getUiSettings();
        uisetitngs.setZoomControlsEnabled(false);
        uisetitngs.setTiltGesturesEnabled(false);
        uisetitngs.setRotateGesturesEnabled(false);
        uisetitngs.setCompassEnabled(false);
        uisetitngs.setRotateGesturesEnabled(false);
        uisetitngs.setMyLocationButtonEnabled(false);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (resultCode == ConnectionResult.SUCCESS){
            Toast.makeText(getApplicationContext(),
                    "isGooglePlayServicesAvailable SUCCESS",
                    Toast.LENGTH_LONG).show();
        }
        else{
            GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
