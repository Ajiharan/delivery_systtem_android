package com.gaya_second.android_pro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gaya_second.android_pro.Model.Delivery;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateDelivery extends AppCompatActivity {
    private EditText contactName,mobileNumber,phoneNumber,address,district,postalCode;
    private Button btnUpdate;
    private FirebaseFirestore db;
    private Delivery delivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delivery);
        db=FirebaseFirestore.getInstance();
        delivery= (Delivery)getIntent().getSerializableExtra("DeliveryClass");
        contactName=findViewById(R.id.contact_name_update);
        mobileNumber=findViewById(R.id.mobile_num_update);
        phoneNumber=findViewById(R.id.phone_num_update);
        address=findViewById(R.id.address_update);
        district=findViewById(R.id.district_update);
        postalCode=findViewById(R.id.postal_code_update);
        btnUpdate=findViewById(R.id.btnUpdate);

        contactName.setText(delivery.getContactName());
        address.setText(delivery.getAddress());
        district.setText(delivery.getDistrict());

        mobileNumber.setText(delivery.getMobileNumber());
        phoneNumber.setText(delivery.getPhoneNumber());
        postalCode.setText(delivery.getPostal());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkEmpty()){
                    db.collection("deliveries").document(delivery.getId())
                            .update(
                                    "contactName", contactName.getText().toString(),
                                    "phoneNumber", phoneNumber.getText().toString(),
                                    "mobileNumber",mobileNumber.getText().toString(),"address",address.getText().toString(),
                                    "district",district.getText().toString(),"postal",postalCode.getText().toString()

                            ).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(UpdateDelivery.this,"Update Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(UpdateDelivery.this,ViewDelivery.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdateDelivery.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(UpdateDelivery.this,ViewDelivery.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                        }
                    });

                }

            }
        });
    }

    private boolean checkEmpty(){
        boolean isCorrect=true;
        if(TextUtils.isEmpty(contactName.getText())){
            contactName.setError("Field is Empty");
            contactName.requestFocus();
            isCorrect=false;
        }
        else{
            contactName.setError(null);
            isCorrect=true;
        }

        if(TextUtils.isEmpty(mobileNumber.getText())){
            mobileNumber.setError("Field is Empty");
            mobileNumber.requestFocus();
            isCorrect=false;
        }else{
            mobileNumber.setError(null);
            isCorrect=true;
        }

        if(TextUtils.isEmpty(phoneNumber.getText())){
            phoneNumber.setError("Field is Empty");
            phoneNumber.requestFocus();
            isCorrect=false;
        }else{
            phoneNumber.setError(null);
            isCorrect=true;
        }
        if(TextUtils.isEmpty(address.getText())){
            address.setError("Field is Empty");
            address.requestFocus();
            isCorrect=false;
        }else{
            address.setError(null);
            isCorrect=true;
        }

        if(TextUtils.isEmpty(district.getText())){
            district.setError("Field is Empty");
            district.requestFocus();
            isCorrect=false;
        }else{
            district.setError(null);
            isCorrect=true;
        }

        if(TextUtils.isEmpty(postalCode.getText())){
            postalCode.setError("Field is Empty");
            postalCode.requestFocus();
            isCorrect=false;
        }else{
            postalCode.setError(null);
            isCorrect=true;
        }
        return  isCorrect;
    }
}