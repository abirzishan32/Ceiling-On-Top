package com.example.ceilingontop;

import static android.content.Intent.getIntent;

import static java.util.Objects.requireNonNull;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class HouseDetailedFrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String houseName, houseAddress, phoneNumber, numRooms, numWashrooms, houseImages;
    Button buttonCall, backButton;

    public HouseDetailedFrag() {
    }

    public HouseDetailedFrag(String houseName, String houseAddress, String phoneNumber, String numRooms, String numWashrooms, String houseImages) {
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.phoneNumber = phoneNumber;
        this.numRooms = numRooms;
        this.numWashrooms = numWashrooms;
        this.houseImages = houseImages;
    }

    public static HouseDetailedFrag newInstance(String param1, String param2) {
        HouseDetailedFrag fragment = new HouseDetailedFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_house_detailed, container, false);
        ImageView houseImage = (ImageView) view.findViewById(R.id.house_image_view);
        TextView houseName = view.findViewById(R.id.house_name);
        TextView houseAddress = view.findViewById(R.id.house_address);
        TextView housePhoneNumber = view.findViewById(R.id.house_phone_number);
        TextView houseNumberOfRooms = view.findViewById(R.id.house_number_of_rooms);
        TextView houseNumberOfWashrooms = view.findViewById(R.id.house_number_of_washrooms);
        buttonCall = view.findViewById(R.id.call_now);
        backButton = view.findViewById(R.id.btn_back7);


        houseName.setText("Name : " + this.houseName);
        houseAddress.setText("Address : " + this.houseAddress);
        housePhoneNumber.setText("Phone Number : " + this.phoneNumber);
        houseNumberOfRooms.setText("Number of Rooms : " + this.numRooms);
        houseNumberOfWashrooms.setText("Number of Washrooms : " + this.numWashrooms);
        Glide.with(requireContext()).load(houseImages).into(houseImage);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(android.net.Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnBackPressed();
            }
        });





        return view;
    }
    public void OnBackPressed(){
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFrag()).addToBackStack(null).commit();
    }





}