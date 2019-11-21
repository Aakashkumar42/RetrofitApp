package com.example.retrofitapp.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofitapp.R;
import com.example.retrofitapp.Storage.SharedPrefManager;


public class HomeFragment extends Fragment {

    private TextView textViewName,textViewEmail,textViewSchool;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewName=view.findViewById(R.id.textViewName);
        textViewEmail=view.findViewById(R.id.textViewEmail);
        textViewSchool=view.findViewById(R.id.textViewSchool);


        textViewName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getName());
        textViewEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmail());
        textViewSchool.setText(SharedPrefManager.getInstance(getActivity()).getUser().getSchool());


    }
}
