package com.example.retrofitapp.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofitapp.R;
import com.example.retrofitapp.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder> {


        private Context mCtx;
        private List<User> userList;

        public UserAdapter(Context mCtx, List<User> userList){
            this.mCtx=mCtx;
            this.userList=userList;

        }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(mCtx).inflate(R.layout.activity_recycle,parent,false);
            return new UsersViewHolder(view);

        }


        @Override
    public void onBindViewHolder(@NonNull UserAdapter.UsersViewHolder holder, int position) {

            User user=userList.get(position);

        holder.textViewEmail.setText(user.getEmail());
        holder.textViewName.setText(user.getName());
        holder.textViewSchool.setText(user.getSchool());


    }

    @Override
    public int getItemCount() {

        return userList.size();
    }

        class UsersViewHolder extends RecyclerView.ViewHolder{

            TextView textViewName,textViewEmail,textViewSchool;


                public UsersViewHolder(View itemview){
                    super(itemview);

                textViewEmail=itemview.findViewById(R.id.textViewEmail);
                textViewName=itemview.findViewById(R.id.textViewName);
                textViewSchool=itemview.findViewById(R.id.textViewSchool);


                }

        }
}
