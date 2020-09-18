package com.gaya_second.android_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.gaya_second.android_pro.Database.DBHelper;
import com.gaya_second.android_pro.Model.Delivery;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper imgBanner;
    private EditText contactName,mobileNumber,phoneNumber,address,district,postalCode;
    private Button btnAdd;
    private View view_toast,view_error_toast;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DBHelper(this);
        imgBanner=findViewById(R.id.select_product_image);
        contactName=findViewById(R.id.contact_name);
        mobileNumber=findViewById(R.id.mobile_num);
        phoneNumber=findViewById(R.id.phone_num);
        address=findViewById(R.id.address);
        district=findViewById(R.id.district);
        postalCode=findViewById(R.id.postal_code);
        btnAdd=findViewById(R.id.btn_submit);
        int sliders[]={R.drawable.banner8,R.drawable.banner7,R.drawable.banner9,R.drawable.banner10};
        for(int slide:sliders){
            bannerFlipper(slide);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkEmpty()){
                    view_toast= LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_success_toast,(LinearLayout)findViewById(R.id.custom_layout));
                    view_error_toast= LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_error_toast,(LinearLayout)findViewById(R.id.custom_layout));

                    Delivery delivery=new Delivery();
                    delivery.setContactName(contactName.getText().toString());
                    delivery.setMobileNumber(Integer.parseInt(mobileNumber.getText().toString()));
                    delivery.setPhoneNumber(Integer.parseInt(phoneNumber.getText().toString()));
                    delivery.setAddress(address.getText().toString());
                    delivery.setDistrict(district.getText().toString());
                    delivery.setPostal(Integer.parseInt(postalCode.getText().toString()));
                    boolean isAdded=db.Insert_delivery_details(delivery);
                    if(isAdded){
                        clearText();
                        ((TextView)view_toast.findViewById(R.id.txt_message)).setText("Data added successfully!!");
                        ((ImageView)view_toast.findViewById(R.id.img_view)).setImageResource(R.drawable.toast_button);
                        Toast toast=new Toast(MainActivity.this);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView( view_toast);
                        toast.show();
                    }else{
                        clearText();
                        ((TextView)view_error_toast.findViewById(R.id.txt_message)).setText("Error Occured");
                        ((ImageView)view_error_toast.findViewById(R.id.img_views)).setImageResource(R.drawable.toast_error_button);
                        Toast toast=new Toast(MainActivity.this);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView( view_error_toast);
                        toast.show();
                    }


                }
            }
        });
    }
    private void clearText(){
        contactName.setText("");
        phoneNumber.setText("");
        mobileNumber.setText("");
        district.setText("");
        postalCode.setText("");
        address.setText("");

    }
    private void bannerFlipper(int image){
        ImageView imgView=new ImageView(this);
        imgView.setImageResource(image);
        imgBanner.addView(imgView);
        imgBanner.setFlipInterval(6000);
        imgBanner.setAutoStart(true);
        imgBanner.setInAnimation(this,android.R.anim.fade_in);
        imgBanner.setOutAnimation(this,android.R.anim.fade_out);
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