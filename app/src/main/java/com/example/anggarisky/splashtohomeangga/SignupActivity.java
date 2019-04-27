package com.example.anggarisky.splashtohomeangga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Services;
import models.User;
import repositories.ServiceRepo;
import repositories.UserRepo;
import retrofit.ApiClient;
import retrofit.ApiService;

/**
 * A login screen that offers login via email/password.
 */
public class SignupActivity extends AppCompatActivity  {

    public ApiService apiInterface;
    UserRepo userRepo;

    private ServiceRepo serviceRepo;
    Button btnSingIn;
    @BindView(R.id.input_firstname)
    EditText _firtstnameText;

    @BindView(R.id.input_lastname) EditText _lastnameText;
    @BindView(R.id.input_name) EditText _nameText;

    @BindView(R.id.national_id) EditText _national_id;
    @BindView(R.id.phone_number) EditText _phonenumber;
    @BindView(R.id.input_email) EditText _emailText;

    @BindView(R.id.input_password) EditText _passwordText;

    @BindView(R.id.link_login)
    TextView _loginLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        // Set up the login form.
        ButterKnife.bind(this);

        apiInterface = ApiClient.getService();

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        btnSingIn = findViewById(R.id.btnSingIn);
        btnSingIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String nationalid = _national_id.getText().toString();
                        String phonenumber = _phonenumber.getText().toString();
                        String email = _emailText.getText().toString();
                        String firstname = _firtstnameText.getText().toString();
                        String lastname = _lastnameText.getText().toString();
                        String password = _passwordText.getText().toString();
                        String username = _nameText.getText().toString();

                        signup(phonenumber,nationalid,username,email,  firstname,lastname,password );

                    }
                }
        );

    }

    private void signup(String phonenumber, String nationalid, String username, String email, String firstname, String lastname, String password) {
        userRepo = new UserRepo(getApplication());
        userRepo.createAccount(new User(username,nationalid,"",phonenumber,email,firstname,lastname,username,password));
        _emailText.setText("");
        _firtstnameText.setText("");
        _national_id.setText("");
        _phonenumber.setText("");
        _lastnameText.setText("");
        _passwordText.setText("");
        _nameText.setText("");
    }

    public  class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            Toast.makeText(parent.getContext(), "Item is " +
                    parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }


}

