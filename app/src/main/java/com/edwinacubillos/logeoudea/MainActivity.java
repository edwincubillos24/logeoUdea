package com.edwinacubillos.logeoudea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView tNombre, tMail, tUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        tNombre = (TextView) findViewById(R.id.tNombre);
        tMail = (TextView) findViewById(R.id.tMail);
        tUID = (TextView) findViewById(R.id.tUID);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user!=null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            String uid = user.getUid();

            tNombre.setText(name);
            tMail.setText(email);
            tUID.setText(uid);
        }else {
            goLoginActivity();
        }
    }

    public void logout(View view){
        LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
        goLoginActivity();
    }

    private void goLoginActivity() {
        Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

