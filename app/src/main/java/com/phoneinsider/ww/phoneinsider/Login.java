package com.phoneinsider.ww.phoneinsider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText et_username, et_password;
    Button btn_login ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = (EditText) findViewById(R.id.l_username);
        et_password = (EditText) findViewById(R.id.l_password);

        btn_login = (Button) findViewById(R.id.login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _username = et_username.getText().toString();
                String _password = et_password.getText().toString();

                try {
                    // fetch the Password form database for respective user name
                    DatabaseHelper db = new DatabaseHelper(Login.this);
                    boolean userExist = db.checkUserExist(_username, _password);
                    // check if the Stored password matches with  Password entered by user
                    if (_username.equals("") || _password.equals("")) {
                        Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_LONG).show();
                    } else if (userExist)
                    {
                        //db.changeLogin_state(_username, _password, "true");
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Login.this, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
    }

}
