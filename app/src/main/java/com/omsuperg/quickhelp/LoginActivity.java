package com.omsuperg.quickhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class LoginActivity
        extends AppCompatActivity
        implements FacebookCallback<LoginResult> {

    private CallbackManager callbackManager;
    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        if (AccessToken.getCurrentAccessToken() != null) {
            Log.d(TAG, "onCreate: Hay una session iniciada");
            startMainActivity(AccessToken.getCurrentAccessToken());
        }


        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        startMainActivity(loginResult.getAccessToken());
    }

    private void startMainActivity(AccessToken accessToken) {
        startActivity(new Intent(this, MainActivity.class)
                .putExtra("TokenSession", accessToken));
        finish();
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "Se cancelo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(this, "Se produjo un error", Toast.LENGTH_SHORT).show();
    }
}
