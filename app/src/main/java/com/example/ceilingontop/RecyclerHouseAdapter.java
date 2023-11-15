package com.example.ceilingontop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ceilingontop.R;
import com.example.ceilingontop.house;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class RecyclerHouseAdapter  extends  FirebaseRecyclerAdapter<house, RecyclerHouseAdapter.myviewholder>{
    List<house> houseListFull;


    public RecyclerHouseAdapter(@NonNull FirebaseRecyclerOptions<house> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull house model) {
        holder.name.setText("House Name : " + model.getHouseName());
        holder.address.setText("Address : " + model.getHouseAddress());
        holder.phone.setText("Phone No : " + model.getPhoneNumber());
        holder.rooms.setText("No of Rooms : " + model.getNumRooms());
        holder.washrooms.setText("No of Washrooms : " + model.getNumWashrooms());
        Glide.with(holder.img.getContext()).load(model.getHouseImages()).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                house house = getItem(position);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new HouseDetailedFrag(house.getHouseName(), house.getHouseAddress(), house.getPhoneNumber(), house.getNumRooms(), house.getNumWashrooms(), house.getHouseImages())).addToBackStack(null).commit();
            }

        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_row, parent, false);
        return new myviewholder(view);
    }




    public void setItems(List<house> houseList) {
        this.houseListFull = new ArrayList<>(houseList);
        notifyDataSetChanged();
    }
    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView name, address, phone, rooms, washrooms;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.imageViewHouse);
            name = (TextView)itemView.findViewById(R.id.houseName);
            address = (TextView)itemView.findViewById(R.id.houseAddress);
            phone = (TextView)itemView.findViewById(R.id.housePhoneNumber);
            rooms = (TextView)itemView.findViewById(R.id.houseNumberOfRooms);
            washrooms = (TextView)itemView.findViewById(R.id.houseNumberOfWashrooms);
        }
    }
}


