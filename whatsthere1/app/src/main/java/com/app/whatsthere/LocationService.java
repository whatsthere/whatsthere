package com.app.whatsthere;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LocationService extends Activity implements  OnClickListener{

    private LocationManager locationManager ;


    private SharedPreferences prefs;
    private TextView lastSavedLocationTextView,myLocation;

    private TextView gpsLocationTextView,networkLocationTextView;
    private TextView gpsAccurecyTextView, networkAccurecyTextView;
    private float lastLon,lastLat;
    private Location lastSavedLocation;
    private MediaPlayer mediaPlayer;
    private Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_service);



        lastSavedLocation = new Location(LocationManager.GPS_PROVIDER);
        lastSavedLocation.setLatitude(0);
        lastSavedLocation.setLongitude(0);

        initControls();
        initDataFromSharedPrefernces();


    }



    private void initDataFromSharedPrefernces() {
        prefs = this.getSharedPreferences(
                "com.cambium.locationserviceproject", Context.MODE_PRIVATE);
        if(prefs.contains("location")){
            lastSavedLocationTextView.setText(prefs.getString("location", "location"));
        }

    }



    private void initControls() {
        startButton = (Button)findViewById(R.id.startStopLocationBtn);
        startButton.setOnClickListener(this);

        lastSavedLocationTextView = (TextView)findViewById(R.id.lastSavedLocation);
        networkAccurecyTextView = (TextView)findViewById(R.id.networkAccurecyTextView);
        networkLocationTextView = (TextView)findViewById(R.id.networkLocationTextView);
        gpsAccurecyTextView  =(TextView)findViewById(R.id.gpsAccurecyTextView);
        networkLocationTextView = (TextView)findViewById(R.id.networkLocationTextView);
        gpsLocationTextView = (TextView)findViewById(R.id.gpsLocationTextView);

    }



    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub

        //get the location service from the device
        if(this.startButton==view){
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

            // get low accuracy provider
            LocationProvider low=
                    locationManager.getProvider(locationManager.getBestProvider(createCoarseCriteria(),true));
            String lowLocationProviderName = low.getName();
            // get high accuracy provider
            LocationProvider high=
                    locationManager.getProvider(locationManager.getBestProvider(createFineCriteria(), true));
            String highLocationProviderName = high.getName();

            Location location = locationManager.getLastKnownLocation(high.getName());
            lastLat =(float) location.getLatitude();
            lastLon = (float)location.getLongitude();
            //  double  distanceFromLastLocation  = location.distanceTo(lastSavedLocation);

            //  if(distanceFromLastLocation<=5)
            //	mediaPlayer.start();
            String str = "Latitude: "+lastLat+ "\n Longitude: "+lastLon+"network provider";
            gpsLocationTextView.setText(str);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1,  new LocationListener() {

                @Override
                public void onLocationChanged(Location location) {
                    lastLat =(float) location.getLatitude();
                    lastLon = (float)location.getLongitude();
                    //  double  distanceFromLastLocation  = location.distanceTo(lastSavedLocation);

                    //  if(distanceFromLastLocation<=5)
                    //	mediaPlayer.start();
                    String str = "Latitude: "+lastLat+ "\n Longitude: "+lastLon+"network provider";
                    gpsLocationTextView.setText(str);
                    if(location.hasAccuracy())
                        gpsAccurecyTextView.setText(Float.toString(location.getAccuracy()));
                }


                @Override
                public void onProviderDisabled(String provider) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();

                }


                @Override
                public void onProviderEnabled(String provider) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();

                }


                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    // TODO Auto-generated method stub
                    if(status==LocationProvider.AVAILABLE)
                        Toast.makeText(getBaseContext(), "GPS is available ", Toast.LENGTH_LONG).show();
                }
            });


            //  locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,0,0, passiveLocationListener);





        }
        else{

            Editor editor = prefs.edit();
            String content = "Latitude: "+lastLat+ "\n Longitude: "+lastLon;
            editor.putString("location1", content);
            editor.commit();
            lastSavedLocation.setLatitude(lastLat);
            lastSavedLocation.setLongitude(lastLon);
        }

    }






    public static Criteria createFineCriteria() {

        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_FINE);
        c.setAltitudeRequired(false);
        c.setBearingRequired(false);
        c.setSpeedRequired(false);
        c.setCostAllowed(true);
        c.setPowerRequirement(Criteria.POWER_HIGH);
        return c;

    }
    public static Criteria createCoarseCriteria() {

        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_COARSE);
        c.setAltitudeRequired(false);
        c.setBearingRequired(false);
        c.setSpeedRequired(false);
        c.setCostAllowed(true);
        c.setPowerRequirement(Criteria.POWER_HIGH);
        return c;

    }



    private LocationListener passiveLocationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            lastLat =(float) location.getLatitude();
            lastLon = (float)location.getLongitude();
            double  distanceFromLastLocation  = location.distanceTo(lastSavedLocation);


            if(distanceFromLastLocation<=5)
                mediaPlayer.start();




            String str = "Latitude: "+lastLat+ "\n Longitude: "+lastLon+"network provider";
            networkLocationTextView.setText(str);
            networkAccurecyTextView.setText(Float.toString(location.getAccuracy()));

            //   Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
        }


        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            Toast.makeText(getBaseContext(), provider+" turned off ", Toast.LENGTH_LONG).show();

        }


        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            Toast.makeText(getBaseContext(), provider+" turned on ", Toast.LENGTH_LONG).show();

        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
    };
}
