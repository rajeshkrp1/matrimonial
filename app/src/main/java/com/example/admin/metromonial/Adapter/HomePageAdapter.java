package com.example.admin.metromonial.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.metromonial.Model.HomeModel;
import com.example.admin.metromonial.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 11/27/2017.
 */

public class HomePageAdapter extends  RecyclerView.Adapter<HomePageAdapter.ViewHolder> {


    ArrayList<HomeModel> arrayList = new ArrayList<>();

    Context context;

    public HomePageAdapter(ArrayList<HomeModel> arrayList, Context context) {


        this.arrayList = arrayList;

        this.context = context;

    }
    @Override
    public HomePageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_item, parent, false);

        HomePageAdapter.ViewHolder recyclerViewHolder = new HomePageAdapter.ViewHolder(view);

        return recyclerViewHolder;

    }
    @Override
    public void onBindViewHolder(HomePageAdapter.ViewHolder holder, int position) {


        HomeModel homeModel = arrayList.get(position);

       // holder.username.setText(homeModel.getUsername());

        //Picasso.with(context).load(homeModel.getUser_image()).into(holder.user_image);

    }

    @Override
    public int getItemCount() {

        return arrayList == null ? 0 : arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username, userdetaols, useraddress,onlinrtxt;
        ImageView star_image,cross_image,tick_image,email_image,onlineimage,user_image;

        public ViewHolder(View itemView) {


            super(itemView);


            username = (TextView) itemView.findViewById(R.id.username);





        }
    }
}


