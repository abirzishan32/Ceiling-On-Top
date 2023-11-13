package com.example.ceilingontop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    RelativeLayout  house_list, search_house, buyer_profile;
    TextView showEmail;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    RecyclerHouseAdapter adapter;


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
        search_house = view.findViewById(R.id.search_house);
        buyer_profile = view.findViewById(R.id.buyer_profile);



        bottomNavigation.show(1, true); //Default fragment

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.house));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.search));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.person));
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

    public void meowNavigation(){
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
//                        loadFragment(new UploadHouseFrag());
                        house_list.setVisibility(View.VISIBLE);
                        search_house.setVisibility(View.GONE);
                        buyer_profile.setVisibility(View.GONE);
                        break;
                    case 2:
//                        loadFragment(new SearchFrag());
                        house_list.setVisibility(View.GONE);
                        search_house.setVisibility(View.VISIBLE);
                        buyer_profile.setVisibility(View.GONE);
                        break;
                    case 3:
//                        loadFragment(new ProfileFrag());
                        house_list.setVisibility(View.GONE);
                        search_house.setVisibility(View.GONE);
                        buyer_profile.setVisibility(View.VISIBLE);
                        break;
                }
                return null;
            }
        });
    }




}