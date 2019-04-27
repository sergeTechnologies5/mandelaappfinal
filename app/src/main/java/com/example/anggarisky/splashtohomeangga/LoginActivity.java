package com.example.anggarisky.splashtohomeangga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.Login;
import models.LoginResponse;
import models.User;
import repositories.UserRepo;
import retrofit.ApiClient;
import retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {


    Button btnSingIn;
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.link_signup)
    TextView _signupLink;
    public ApiService apiInterface;


    public static int user_id = 0;
    EditText input_email;
    EditText input_password;
    UserRepo userRepo;
    public static  User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        // Set up the login form.
        btnSingIn = findViewById(R.id.btn_login);

        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        ButterKnife.bind(this);


        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        btnSingIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        userRepo = new UserRepo(getApplication());
                        final String email = input_email.getText().toString();
                        final String password = input_password.getText().toString();

                        apiInterface = ApiClient.getService();
                        Call<LoginResponse> call = apiInterface.login(new Login(password,email));
                        call.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {

                                User user = response.body().getUser();
                                if (user==null){
                                    Toast.makeText(getApplicationContext(),"Use a different Password or Email", Toast.LENGTH_LONG).show();
                                }else {
                                    user_id = (int)user.getId();
                                    currentUser = user;
                                    startActivity(new Intent(getApplicationContext(), NavigationDrawer.class));
                                }


                            }
                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),"Use a valid email or password",Toast.LENGTH_LONG).show();

                            }

                        });


                    }
                }
        );

    }


}

