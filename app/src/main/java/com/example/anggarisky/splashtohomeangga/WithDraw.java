package com.example.anggarisky.splashtohomeangga;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import models.Payment;
import models.Response;
import models.User;
import retrofit.ApiClient;
import retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;


public class WithDraw extends Fragment {


    public ApiService apiInterface;
    TextView confirmation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_with_draw, container, false);
        final EditText amount = view.findViewById(R.id.amount);
        Button savebtn = view.findViewById(R.id.save);
        confirmation = view.findViewById(R.id.confirmation);

        apiInterface = ApiClient.getService();

        savebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String amountfee = amount.getText().toString();

                        User user = LoginActivity.currentUser;
                        Call<Response> call = apiInterface.withDrawservices( new Payment(user.getId(),user.getGroup_id(),user.getPhonenumber(),amountfee));
                        call.enqueue(new Callback<Response>() {

                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                                Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_LONG).show();
                            }

                        });

                        amount.setText("");
                        confirmation.setText("Wait for a confirmation notification");
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
