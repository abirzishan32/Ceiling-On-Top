package com.example.ceilingontop;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class ActivityUploadHouse extends AppCompatActivity {
    private EditText houseName, houseAddress, numRooms, numWashrooms, phoneNumber;
    private Button uploadButton, back;
    private ImageView houseImageView;

    private FirebaseAuth mAuth;
    private DatabaseReference houseDatabase;
    private StorageReference storageReference;

    private Uri imageURI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_house);

        houseName = findViewById(R.id.input_house_name);
        houseAddress = findViewById(R.id.input_house_address);
        numRooms = findViewById(R.id.input_num_rooms);
        numWashrooms = findViewById(R.id.input_num_washrooms);
        phoneNumber = findViewById(R.id.input_phone_number);
        uploadButton = findViewById(R.id.btn_upload);
        houseImageView = findViewById(R.id.house_image_view);
        back = findViewById(R.id.btn_back3);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String sellerId = currentUser.getUid();

        houseDatabase = FirebaseDatabase.getInstance().getReference("houses");
        storageReference = FirebaseStorage.getInstance().getReference("house_images");

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            imageURI = data.getData();
                            houseImageView.setImageURI(imageURI);
                        }
                        else {
                            Toast.makeText(ActivityUploadHouse.this, "Image not selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        houseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photopicker = new Intent();
                photopicker.setAction(Intent.ACTION_GET_CONTENT);
                photopicker.setType("image/*");
                activityResultLauncher.launch(photopicker);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadHouseInfo(sellerId) == false) {
                    Toast toast = Toast.makeText(ActivityUploadHouse.this, "Please fill in all the fields", Toast.LENGTH_SHORT);
                    return;
                }
                Intent intent = new Intent(ActivityUploadHouse.this, HouseUploadSuccessful.class);
                startActivity(intent);
            }

            private boolean validateInput() {
                String name = houseName.getText().toString().trim();
                String address = houseAddress.getText().toString().trim();
                String roomsStr = numRooms.getText().toString();
                String washroomsStr = numWashrooms.getText().toString();
                String phone = phoneNumber.getText().toString().trim();

                if (name.isEmpty()) {
                    houseName.setError("House name is required");
                    houseName.requestFocus();
                    return false;
                }

                if (address.isEmpty()) {
                    houseAddress.setError("House address is required");
                    houseAddress.requestFocus();
                    return false;
                }

                if (roomsStr.isEmpty()) {
                    numRooms.setError("Number of rooms is required");
                    numRooms.requestFocus();
                    return false;
                }

                if (washroomsStr.isEmpty()) {
                    numWashrooms.setError("Number of washrooms is required");
                    numWashrooms.requestFocus();
                    return false;
                }

                int rooms = Integer.parseInt(roomsStr);
                int washrooms = Integer.parseInt(washroomsStr);

                if (phone.isEmpty()) {
                    phoneNumber.setError("Phone number is required");
                    phoneNumber.requestFocus();
                    return false;
                }

                return true;
            }

            public boolean uploadHouseInfo (String sellerId){
                if (!validateInput()) {
                    return false;
                }
                String name = houseName.getText().toString().trim();
                String address = houseAddress.getText().toString().trim();
                String rooms = numRooms.getText().toString().trim();
                String washrooms = numWashrooms.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();
                String houseImages = imageURI.toString();

                house house = new house(name, address, rooms, washrooms, phone, houseImages);
                house.setSellerId(sellerId);

                DatabaseReference newHouseRef = houseDatabase.push();
                String houseId = newHouseRef.getKey();
                house.setHouseId(houseId);

                newHouseRef.setValue(house).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ActivityUploadHouse.this, "House information uploaded successfully", Toast.LENGTH_SHORT).show();
                            uploadHouseImages(houseId);


                        } else {
                            Toast.makeText(ActivityUploadHouse.this, "Failed to upload house information", Toast.LENGTH_SHORT).show();
                        }
                    }

                    private void uploadHouseImages (String houseId){
                        String ImageKey = houseDatabase.child("house").child(houseId).child("houseImages").push().getKey();
                        StorageReference imageRef = storageReference.child(houseId).child(ImageKey);

                        imageRef.putFile(imageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()) {
                                    Toast toast = Toast.makeText(ActivityUploadHouse.this, "House images uploaded successfully", Toast.LENGTH_SHORT);
                                } else {
                                    Toast toast = Toast.makeText(ActivityUploadHouse.this, "Failed to upload house images", Toast.LENGTH_SHORT);
                                }
                            }
                        });
                    }

                });


                return true;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityUploadHouse.this, ActivityBeforeUploadHouse.class);
                startActivity(intent);
            }
        });


    }
}