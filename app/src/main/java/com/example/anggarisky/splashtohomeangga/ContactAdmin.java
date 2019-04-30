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

import models.Comment;
import models.Payment;
import models.Response;
import models.User;
import retrofit.ApiClient;
import retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;


public class ContactAdmin extends Fragment {


    public ApiService apiInterface;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_contact_admin, container, false);

        Button commentbtn = view.findViewById(R.id.commentbtn);
        final EditText comment = view.findViewById(R.id.comment);

        apiInterface = ApiClient.getService();
        commentbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String comm = comment.getText().toString();

                        User  user = LoginActivity.currentUser;
                        Call<Response> call = apiInterface.comment( new Comment(String.valueOf( user.getId()),user.getGroup_id(),comm));
                        call.enqueue(new Callback<Response>() {

                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                                comment.setText("");
                                Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                 Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_LONG).show();
                            }

                        });


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
