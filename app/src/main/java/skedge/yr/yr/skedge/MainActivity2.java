package skedge.yr.yr.skedge;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity2 extends AppCompatActivity {
    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.skedge.R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = (LoginButton)findViewById(com.example.skedge.R.id.fb_login_bn);
        textView = (TextView)findViewById(com.example.skedge.R.id.textViewMain);
        callbackManager = CallbackManager.Factory.create();
        if (googleServicesAvailable())
        {
            //Toast.makeText(this,"good",Toast.LENGTH_LONG).show();
        }
        /*
        boolean ans = isLoggedIn();
        if (!ans)
        {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        */



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
           // Toast.makeText(this,"cant connect",Toast.LENGTH_LONG).show();
        }
        return false;

    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }


    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    public void MoveToEmpty(View view) {
        Intent i = new Intent(getApplicationContext(),empty.class);
        startActivity(i);
    }

    public void MoveToSearch(View view) {
        Intent i = new Intent(getApplicationContext(),Searching.class);
        startActivity(i);
    }

    public void MoveToGPS(View view) {
        /*
        Intent i = new Intent(getApplicationContext(),mapa.class);
        startActivity(i);
        */
        Toast.makeText(getApplicationContext(),"אפשרות זאת תהיה זמינה בהמשך",Toast.LENGTH_SHORT).show();
    }

    public void MoveToMicro(View view) {
        Intent i = new Intent(getApplicationContext(),Micro.class);
        startActivity(i);
    }
}
