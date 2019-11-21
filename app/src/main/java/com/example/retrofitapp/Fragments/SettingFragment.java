package com.example.retrofitapp.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.retrofitapp.Api.RetrofitClient;
import com.example.retrofitapp.R;
import com.example.retrofitapp.Storage.SharedPrefManager;
import com.example.retrofitapp.activities.LoginActivity;
import com.example.retrofitapp.activities.MainActivity;
import com.example.retrofitapp.model.DefaultResponse;
import com.example.retrofitapp.model.LoginResponse;
import com.example.retrofitapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SettingFragment extends Fragment implements View.OnClickListener {

        private EditText editTextName,editTextEmail,editTextSchool;
        private EditText editTextCurrentPassword,editTextNewPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            editTextName=view.findViewById(R.id.editTextName);
            editTextEmail=view.findViewById(R.id.editTextEmail);
            editTextSchool=view.findViewById(R.id.editTextSchool);
            editTextCurrentPassword=view.findViewById(R.id.editTextCurrentPassword);
            editTextNewPassword=view.findViewById(R.id.editTextNewPassword);


            view.findViewById(R.id.buttonSave).setOnClickListener(this);
            view.findViewById(R.id.buttonLogout).setOnClickListener(this);
            view.findViewById(R.id.buttonChangePassword).setOnClickListener(this);
            view.findViewById(R.id.buttonDeleteprofile).setOnClickListener(this);

    }

    private void userUpdateProfile(){

        String name=editTextName.getText().toString().trim();
        String email=editTextName.getText().toString().trim();
        String school=editTextName.getText().toString().trim();

        if (name.isEmpty()){
            editTextName.setError("name required");
            editTextName.requestFocus();
            return;

        }
        if (email.isEmpty()){
            editTextEmail.setError("email required");
            editTextEmail.requestFocus();
            return;

        }
        if (school.isEmpty()){
            editTextSchool.setError("school required");
            editTextSchool.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Enter a vaild email id");
            editTextEmail.requestFocus();
            return;

        }

      User user= SharedPrefManager.getInstance(getActivity()).getUser();


        Call<LoginResponse> call= RetrofitClient.
                                    getInstance()
                                .     getApi().updateUser(
                                        user.getId(),
                                email,name,school );

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                if (!response.body().isError()){
                    SharedPrefManager.getInstance(getActivity()).saveUser(response.body().getUser());

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

    private void updatePassword(){

        String currentpassword=editTextCurrentPassword.getText().toString().trim();
        String newpassword=editTextNewPassword.getText().toString().trim();


        if (currentpassword.isEmpty()){
            editTextCurrentPassword.setError("enter the password");
            editTextCurrentPassword.requestFocus();
            return;

        }

        if (newpassword.isEmpty()){
            editTextNewPassword.setError("enter the new password");
            editTextNewPassword.requestFocus();
            return;

        }


        User user=SharedPrefManager.getInstance(getActivity()).getUser();

        Call<DefaultResponse> call=RetrofitClient.getInstance().getApi()
                .updatePassword(currentpassword,newpassword,user.getEmail());

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

        private void logout(){

            SharedPrefManager.getInstance(getActivity()).clear();
            Intent intent= new Intent(getActivity(),LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        private void deleteuser(){

            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            builder.setTitle("are you sure?");
            builder.setMessage("This action is irreversible");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    User user=SharedPrefManager.getInstance(getActivity()).getUser();
                    Call<DefaultResponse> call=RetrofitClient.getInstance().getApi().deleteUser(user.getId());

                    call.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                            if (!response.body().isErr()){

                                SharedPrefManager.getInstance(getActivity()).clear();
                                SharedPrefManager.getInstance(getActivity()).clear();
                                Intent intent=new Intent(getActivity(),MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {

                        }
                    });


                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog ad=builder.create();
            ad.show();
        }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonSave:
                    userUpdateProfile();
                    break;
            case R.id.buttonChangePassword:
                        updatePassword();
                        break;
            case R.id.buttonDeleteprofile:
                        deleteuser();
                        break;
            case R.id.buttonLogout:
                        logout();

            }



        }


    }



