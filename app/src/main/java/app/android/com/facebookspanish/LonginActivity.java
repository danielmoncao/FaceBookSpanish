package app.android.com.facebookspanish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class LonginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longin);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);

        //permissao email do usuario
        loginButton.setReadPermissions(Arrays.asList("email"));

        //permissao amigos usuarios
        loginButton.setReadPermissions(Arrays.asList("user_friends"));

        //permissao likkes dos amigos
        loginButton.setReadPermissions(Arrays.asList("user_likes"));



        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                goMainScreen();

            }

            @Override
            public void onCancel() {

                Toast.makeText(getApplicationContext(), R.string.com_facebook_loginview_cancel_action, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(getApplicationContext(), R.string.com_facebook_loginview_log_out_action, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void goMainScreen() {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultoCode, Intent data){
        super.onActivityResult(requestCode, resultoCode, data);
        callbackManager.onActivityResult(requestCode, resultoCode, data);

    }
}
