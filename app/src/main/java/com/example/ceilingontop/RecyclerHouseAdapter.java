package com.example.ceilingontop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ceilingontop.R;
import com.example.ceilingontop.house;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecyclerHouseAdapter  extends  FirebaseRecyclerAdapter<house, RecyclerHouseAdapter.myviewholder>{

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
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_row, parent, false);
        return new myviewholder(view);
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



//public class RecyclerHouseAdapter  extends FirebaseRecyclerAdapter<house, RecyclerHouseAdapter.HouseViewHolder>{
//
//    public RecyclerHouseAdapter(@NonNull FirebaseRecyclerOptions <house> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull HouseViewHolder holder, int position, @NonNull house model) {
//        // Bind the data to the views in the ViewHolder
//        holder.bind(model);
//
//    }
//
//    @NonNull
//    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_row, parent, false);
//        return new HouseViewHolder(view);
//    }
//
//    public static class HouseViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView houseImageView;
//        private TextView houseNameTextView;
//        private TextView houseAddressTextView;
//
//        private TextView housePhoneNumberTextView;
//        private TextView houseNumberOfRoomsTextView;
//        private TextView houseNumberOfWashroomsTextView;
//        public HouseViewHolder(@NonNull View itemView) {
//            super(itemView);
//            houseImageView = itemView.findViewById(R.id.imageViewHouse);
//            houseNameTextView = itemView.findViewById(R.id.houseName);
//            houseAddressTextView = itemView.findViewById(R.id.houseAddress);
//            housePhoneNumberTextView = itemView.findViewById(R.id.housePhoneNumber);
//            houseNumberOfRoomsTextView = itemView.findViewById(R.id.houseNumberOfRooms);
//            houseNumberOfWashroomsTextView = itemView.findViewById(R.id.houseNumberOfWashrooms);
//
//        }
//
//        public void bind(house model) {
//            // Bind data to the views
//            houseNameTextView.setText(model.getHouseName());
//            houseAddressTextView.setText(model.getHouseAddress());
//            housePhoneNumberTextView.setText(model.getPhoneNumber());
//            houseNumberOfRoomsTextView.setText(model.getNumRooms());
//            houseNumberOfWashroomsTextView.setText(model.getNumWashrooms());
//            Picasso.get().load(model.getHouseImages()).into(houseImageView);
//        }
//    }
//}
