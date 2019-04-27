package com.example.anggarisky.splashtohomeangga;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import models.Payment;
import models.PaymentResponse;
import models.User;
import retrofit.ApiClient;
import retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Save extends Fragment {

    User user;
    Button save;
    EditText amount;
    String amountfee ="";
    TextView confirmation;
    public ApiService apiInterface;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_save, container, false);


        save = view.findViewById(R.id.save);
        amount = view.findViewById(R.id.amount);
        user = LoginActivity.currentUser;
        confirmation = view.findViewById(R.id.confirmation);

        apiInterface = ApiClient.getService();
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        amountfee = amount.getText().toString();
                        Call<PaymentResponse> call = apiInterface.payForservices( new Payment(user.getId(),user.getGroup_id(),user.getPhonenumber(),amountfee));
                        call.enqueue(new Callback<PaymentResponse>() {
                            @Override
                            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {

                                Toast.makeText(getContext(), response.message(),Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                                // Toast.makeText(getApplication(), t.getMessage(),Toast.LENGTH_LONG).show();
                            }

                        });

                        amount.setText("");
                        amount.setEnabled(false);
                        save.setEnabled(false);
                        confirmation.setText("Wait for a mpesa push notification");
                    }
                }
        );
        return view;


    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
