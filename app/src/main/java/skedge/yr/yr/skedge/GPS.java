package skedge.yr.yr.skedge;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

public class GPS extends AppCompatActivity {


    public String tM;
    public Location current;
    public LocationManager locationManager;
    public LocationListener locationListener;
    GPSTracker gps;
    Location curr;
    Location dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.skedge.R.layout.activity_gps);



        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                curr.setLatitude(location.getLatitude());
                curr.setLongitude(location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };


                locationManager.requestLocationUpdates("gps",3000,0,locationListener);
                gps = new GPSTracker(GPS.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    curr = new Location("current");
                    curr.setLatitude(latitude);
                    curr.setLongitude(longitude);




                    // \n is for new line

                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
            }




                //new GetDistance(32.774052, 34.993352,32.774713, 34.992731);


    }




