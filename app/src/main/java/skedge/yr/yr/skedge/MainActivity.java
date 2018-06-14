package skedge.yr.yr.skedge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {
    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.skedge.R.layout.activity_main2);
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = (LoginButton)findViewById(com.example.skedge.R.id.fb_login_bn);
        callbackManager = CallbackManager.Factory.create();
        boolean ans = isLoggedIn();
        if (ans)
        {

            Intent i = new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(i);

        }

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {

                    Intent i = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(i);

                }

                @Override
                public void onCancel() {

                    Toast.makeText(getApplicationContext(),"אחי, ביקשתי להתחבר, מה אתה עושה קטעים",Toast.LENGTH_LONG).show();

                }

                @Override
                public void onError(FacebookException error) {
                    Toast.makeText(getApplicationContext(),"אחי, ביקשתי להתחבר, מה אתה עושה קטעים",Toast.LENGTH_LONG).show();

                }
            });
    }
    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
