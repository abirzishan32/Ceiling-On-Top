package com.example.ceilingontop;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class HomeFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MeowBottomNavigation bottomNavigation;
    RelativeLayout house_list, services, map, buyer_profile;
    TextView showEmail;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    RecyclerHouseAdapter adapter;
    CardView wifi, water, electricity;
    SupportMapFragment smf;
    FusedLocationProviderClient client;

    public HomeFrag() {
        // Required empty public constructor
    }


    public static HomeFrag newInstance(String param1, String param2) {
        HomeFrag fragment = new HomeFrag();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bottomNavigation = view.findViewById(R.id.bottom_navigation); // Initialize bottomNavigation here
        house_list = view.findViewById(R.id.list_of_house);
        map = view.findViewById(R.id.map);
        services = view.findViewById(R.id.services);
        buyer_profile = view.findViewById(R.id.buyer_profile);


        smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        client = new FusedLocationProviderClient(getContext());

        Dexter.withContext(getContext()).withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                getmylocation();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                //Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                //Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
            }
        }).check();



        wifi = view.findViewById(R.id.wifi);
        water = view.findViewById(R.id.water);
        electricity = view.findViewById(R.id.electricity);

        bottomNavigation.show(1, true); //Default fragment

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.house));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.servies));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.map));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.person));
        meowNavigation();


        FirebaseUser currentUser;
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        showEmail = view.findViewById(R.id.show_email);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            String userEmail = firebaseAuth.getCurrentUser().getEmail();

            if (userEmail != null) {
                showEmail.setText("Hi, " + userEmail + "! \nWelcome to Ceiling On Top!");
            }
        }


        if (currentUser != null) {
            String currentUserId = currentUser.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("houses").child(currentUserId);

            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            FirebaseRecyclerOptions<house> options =
                    new FirebaseRecyclerOptions.Builder<house>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("houses"), house.class).build();
            adapter = new RecyclerHouseAdapter(options);
            recyclerView.setAdapter(adapter);
        }

        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiFrag wifiFrag = new WifiFrag();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.container, wifiFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaterFrag waterFrag = new WaterFrag();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.container, waterFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ElectricityFrag electricityFrag = new ElectricityFrag();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.container, electricityFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void meowNavigation() {
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        house_list.setVisibility(View.VISIBLE);
                        services.setVisibility(View.GONE);
                        map.setVisibility(View.GONE);
                        buyer_profile.setVisibility(View.GONE);
                        break;
                    case 2:
                        house_list.setVisibility(View.GONE);
                        services.setVisibility(View.VISIBLE);
                        map.setVisibility(View.GONE);
                        buyer_profile.setVisibility(View.GONE);
                        break;
                    case 3:
                        house_list.setVisibility(View.GONE);
                        services.setVisibility(View.GONE);
                        map.setVisibility(View.VISIBLE);
                        buyer_profile.setVisibility(View.GONE);
                        break;
                    case 4:
                        house_list.setVisibility(View.GONE);
                        services.setVisibility(View.GONE);
                        map.setVisibility(View.GONE);
                        buyer_profile.setVisibility(View.VISIBLE);
                        break;

                }
                return null;
            }
        });
    }

    public void getmylocation() {
        if ((ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                smf.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here!");

                        googleMap.addMarker(markerOptions);
                        googleMap.animateCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(latLng, 17));
                    }
                });
            }
        });



    }




}