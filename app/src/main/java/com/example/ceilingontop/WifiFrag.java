package com.example.ceilingontop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class WifiFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private static final String JSON_URL = "https://api.myjson.online/v1/records/ee631000-aecb-4fe6-9544-c08bd402c111";
    private ListView listView;
    private ArrayList<String> providerDetailsList;
    public WifiFrag() {
        // Required empty public constructor
    }

    public static WifiFrag newInstance(String param1, String param2) {
        WifiFrag fragment = new WifiFrag();
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
        View view = inflater.inflate(R.layout.fragment_wifi, container, false);
        listView = view.findViewById(R.id.listViewWifi);
        providerDetailsList = new ArrayList<>();
        fetchData();



        return view;
    }


    private void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONArray providersArray = data.getJSONArray("providers");

                            for (int i = 0; i < providersArray.length(); i++) {
                                JSONObject provider = providersArray.getJSONObject(i);
                                String providerName = provider.getString("name");
                                String providerLocation = provider.getString("location");
                                String providerPhone = provider.getString("phone");
                                JSONArray plansArray = provider.getJSONArray("plans");

                                StringBuilder providerDetails = new StringBuilder();
                                providerDetails.append("Provider : ").append(providerName).append("\n")
                                        .append("Phone : ").append(providerPhone).append("\n")
                                        .append("Location : ").append(providerLocation).append("\n")
                                        .append("Plans :\n");

                                for (int j = 0; j < plansArray.length(); j++) {
                                    JSONObject plan = plansArray.getJSONObject(j);
                                    String price = plan.getString("price");
                                    String speed = plan.getString("speed");
                                    providerDetails.append("        - ").append(speed).append(": ").append(price).append("\n");
                                }

                                providerDetailsList.add(providerDetails.toString());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, providerDetailsList);
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        queue.add(jsonObjectRequest);
    }

    public void OnBackPressed(){
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFrag()).addToBackStack(null).commit();
    }

}

