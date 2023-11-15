package com.example.ceilingontop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class WaterFrag extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private static final String JSON_URL = "https://api.myjson.online/v1/records/023e3e41-ffb3-4511-ba52-ab18b42a781c";
    private List<String> electricityProviderDetails;
    private ArrayAdapter<String> arrayAdapter;

    public WaterFrag() {
        // Required empty public constructor
    }

    public static WaterFrag newInstance(String param1, String param2) {
        WaterFrag fragment = new WaterFrag();
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
        View view = inflater.inflate(R.layout.fragment_water, container, false);
        ListView listView = view.findViewById(R.id.listViewWater);
        electricityProviderDetails = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, electricityProviderDetails);
        listView.setAdapter(arrayAdapter);
        fetchData();
        return view;
    }

    private void fetchData() {
        String url = "https://api.myjson.online/v1/records/023e3e41-ffb3-4511-ba52-ab18b42a781c";

        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse JSON response
                            parseJson(response);
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
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
    private void parseJson(JSONObject response) throws JSONException {
        JSONArray providersArray = response.getJSONObject("data").getJSONArray("water_providers");

        for (int i = 0; i < providersArray.length(); i++) {
            JSONObject providerObject = providersArray.getJSONObject(i);

            String name = providerObject.getString("name");
            String phone = providerObject.getString("phone");
            String location = providerObject.getString("location");

            String providerDetails = "Name: " + name + "\nPhone: " + phone + "\nLocation: " + location;
            electricityProviderDetails.add(providerDetails);
        }

        arrayAdapter.notifyDataSetChanged();
    }
}