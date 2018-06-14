package skedge.yr.yr.skedge;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class mapa extends AppCompatActivity implements OnMapReadyCallback {

    public String tM;
    public Location current;
    public LocationManager locationManager;
    public LocationListener locationListener;
    GPSTracker gps;
    Location curr;
    Location dest;
    GoogleMap mGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        gps = new GPSTracker(mapa.this);

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


    if (googleServicesAvailable())
        {
            //Toast.makeText(this,"good",Toast.LENGTH_LONG).show();
            setContentView(com.example.skedge.R.layout.activity_mapa);
            initMap();
        }
        else
        {

        }




    }


    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(com.example.skedge.R.id.MapFragment);
        mapFragment.getMapAsync(this);

    }

    public boolean googleServicesAvailable()
    {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailible = api.isGooglePlayServicesAvailable(this);
        if (isAvailible == ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if (api.isUserResolvableError(isAvailible))
        {
            Dialog dialog = api.getErrorDialog(this,isAvailible,0);
            dialog.show();
        }
        else
        {
            Toast.makeText(this,"cant connect",Toast.LENGTH_LONG).show();
        }
        return false;

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        goToLocationZoom(curr.getLatitude(), curr.getLongitude(),19);
    }

    private void goToLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat,lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);

    }
    private void goToLocationZoom(double lat, double lng,float zoom) {
        LatLng ll = new LatLng(lat,lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mGoogleMap.moveCamera(update);

    }
}
