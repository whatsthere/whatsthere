package com.app.whatsthere.locationservice;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by maratibragimov on 11/30/14.
 */
public class WtLocationServices  {

    private LocationManager locationManager;
    private static WtLocationServices wtLocationServices;
    private  Context context;
    public float lastLat,lastLon;
    private float gpsAccuracy = Float.MAX_VALUE;
    private float networkAccuracy = Float.MAX_VALUE;
    private int gpsMinTime = 0;
    private int gpsMinDistance = 0;
    private int networkMinTime = 0;
    private int networkMinDistance = 0;
    private boolean isGpsProviderAvailable;
    private boolean isNetworkProviderAvailable;
    private Location gpsLocation;
    private Location netWorkLocation;
    private Location bestLocation;
    private LocationListener gpsLocationListener = new LocationListener() {

        private boolean isBestAccuracy;
        @Override
        public void onLocationChanged(Location location) {

            gpsAccuracy = location.getAccuracy();

            if(gpsAccuracy<networkAccuracy) {

                bestLocation = location;
                if(gpsAccuracy<=50&&isBestAccuracy==false){
                    gpsMinDistance = 25;
                    gpsMinTime = 5000;
                    isBestAccuracy = true;
                    locationManager.removeUpdates(gpsLocationListener);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,gpsMinTime,gpsMinDistance,this);
                    Toast.makeText(context,"gps accucry is the best",Toast.LENGTH_SHORT).show();
                }
                lastLat = (float) location.getLatitude();
                lastLon = (float) location.getLongitude();
                gpsLocation = location;
                String locationStr = String.valueOf(lastLat) + "," + String.valueOf(lastLon);
                String accuracy = String.valueOf(gpsAccuracy);
                Toast.makeText(context,"gps provider :"+ locationStr +" with accuracy of :" +accuracy, Toast.LENGTH_SHORT).show();
            }

        }


        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            Toast.makeText(context, "Gps turned off ", Toast.LENGTH_LONG).show();
            isBestAccuracy = false;
            gpsAccuracy = Float.MAX_VALUE;
            gpsMinDistance = 0;
            gpsMinTime = 0;
            isGpsProviderAvailable = false;
        }


        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            isGpsProviderAvailable = true;
            Toast.makeText(context, "Gps turned on ", Toast.LENGTH_LONG).show();


        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
            if(status== LocationProvider.AVAILABLE)
                Toast.makeText(context, "GPS is available ", Toast.LENGTH_SHORT).show();
        }

    };

    private LocationListener networkLocationListener = new LocationListener() {
        private boolean isBestAccuracy;
        @Override
        public void onLocationChanged(Location location) {

            networkAccuracy = location.getAccuracy();
            if(networkAccuracy < gpsAccuracy) {
                bestLocation = location;
                if(networkAccuracy<=50&&isBestAccuracy==false){

                    networkMinDistance = 25;
                    networkMinTime = 5000;
                    isBestAccuracy = true;
                    locationManager.removeUpdates(networkLocationListener);
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,networkMinTime,networkMinDistance,this);
                    Toast.makeText(context,"network location service is the best",Toast.LENGTH_SHORT).show();
                }
                netWorkLocation = location;
                lastLat = (float) location.getLatitude();
                lastLon = (float) location.getLongitude();
                String locationStr = String.valueOf(lastLat) + "," + String.valueOf(lastLon);
                String accuracy = String.valueOf(networkAccuracy);
                Toast.makeText(context, "network provider :" + locationStr +" with accuracy : "+accuracy, Toast.LENGTH_SHORT).show();
            }

        }


        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            Toast.makeText(context, "network location service turned off ", Toast.LENGTH_LONG).show();
            networkAccuracy = Float.MAX_VALUE;
            isBestAccuracy = false;
            networkMinDistance = 0;
            networkMinTime = 0;
            isNetworkProviderAvailable = false;

        }


        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            Toast.makeText(context, "network location service turned on ", Toast.LENGTH_LONG).show();
            isNetworkProviderAvailable = true;

        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
            if(status== LocationProvider.AVAILABLE)
                Toast.makeText(context, "network location service is available ", Toast.LENGTH_LONG).show();
        }
    };
    public  static  WtLocationServices getInstance(Context context){

        if(wtLocationServices==null){

            wtLocationServices = new WtLocationServices();
            wtLocationServices.context = context;
            wtLocationServices.startLocationServices();

        }

        return  wtLocationServices;

    }
    private  WtLocationServices(){

    }

    private  void startLocationServices(){

        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        // get low accuracy provider
        LocationProvider networkProvider=
                locationManager.getProvider(locationManager.getBestProvider(createCoarseCriteria(),true));

        String lowLocationProviderName = networkProvider.getName();
        // get high accuracy provider
        LocationProvider gpsLocationProvider=
                locationManager.getProvider(locationManager.getBestProvider(createFineCriteria(), true));
        String highLocationProviderName = gpsLocationProvider.getName();

        netWorkLocation = locationManager.getLastKnownLocation(lowLocationProviderName);
        gpsLocation = locationManager.getLastKnownLocation(highLocationProviderName);

        isNetworkProviderAvailable = locationManager.isProviderEnabled(lowLocationProviderName);
        isGpsProviderAvailable = locationManager.isProviderEnabled(highLocationProviderName);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,gpsMinTime, gpsMinDistance,gpsLocationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,networkMinTime,networkMinDistance,networkLocationListener);
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

    public Location getNetWorkLocation() {
        return netWorkLocation;
    }

    public void setNetWorkLocation(Location netWorkLocation) {
        this.netWorkLocation = netWorkLocation;
    }

    public Location getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(Location gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public boolean isGpsProviderAvailable() {
        return isGpsProviderAvailable;
    }

    public void setGpsProviderAvailable(boolean isGpsProviderAvailable) {
        this.isGpsProviderAvailable = isGpsProviderAvailable;
    }


    public boolean isNetworkProviderAvailable() {
        return isNetworkProviderAvailable;
    }

    public void setNetworkProviderAvailable(boolean isNetworkProviderAvailable) {
        this.isNetworkProviderAvailable = isNetworkProviderAvailable;
    }

    public Location getBestLocation() {
        return bestLocation;
    }

    public void setBestLocation(Location bestLocation) {
        this.bestLocation = bestLocation;
    }

}

