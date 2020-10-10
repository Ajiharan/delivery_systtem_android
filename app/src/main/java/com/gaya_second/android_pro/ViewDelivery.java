package com.gaya_second.android_pro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.gaya_second.android_pro.Adaptors.DeliveryAdapter;
import com.gaya_second.android_pro.Model.Delivery;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewDelivery extends AppCompatActivity {
    private Button backButton;
    private SwipeMenuListView listViews;
    private ArrayList<Delivery> dataArrayList;
    private DeliveryAdapter listAdapter;
    private FirebaseFirestore db ;
    private  String m_Text;
    private ImageView addImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_delivery);
        db = FirebaseFirestore.getInstance();
        backButton=findViewById(R.id.back_btn);
        dataArrayList = new ArrayList<>();
        listViews = (SwipeMenuListView) findViewById(R.id.view_delivery_lists);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        retrive_timer_details();
    }

    private void retrive_timer_details(){

        getAllData();
        listAdapter = new DeliveryAdapter(ViewDelivery.this, dataArrayList);
        listViews.setAdapter(listAdapter);

        listViews.setMenuCreator(creator);
        listViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                final Delivery del=dataArrayList.get(i);
                Intent intent=new Intent(ViewDelivery.this,UpdateDelivery.class);
                intent.putExtra("DeliveryClass", del);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });
        listViews.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        delete_cur_alarm(position);
                        dataArrayList.remove(position);
                        listAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        break;
                }
                return false;
            }
        });

    }

    private void getAllData(){
        db.collection("deliveries")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Delivery del=new Delivery();
                                del.setId(document.getId());
                                del.setContactName(document.get("contactName").toString());
                               del.setDistrict(document.get("district").toString());
                               del.setAddress(document.get("address").toString());
                               del.setPhoneNumber(document.get("phoneNumber").toString().trim());
                                del.setMobileNumber(document.get("mobileNumber").toString().trim());
                                del.setPostal(document.get("postal").toString().trim());
                                dataArrayList.add(del);
                            }
                            listAdapter = new DeliveryAdapter(ViewDelivery.this, dataArrayList);
                            listViews.setAdapter(listAdapter);
                            listViews.setMenuCreator(creator);
                        } else {
                            Toast.makeText(ViewDelivery.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void delete_cur_alarm(int position){
       Delivery delivery;
        delivery=dataArrayList.get(position);
        db.collection("deliveries").document(delivery.getId())
                .delete().addOnSuccessListener(new OnSuccessListener< Void >() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ViewDelivery.this, "Sucessfully Deleted",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    SwipeMenuCreator creator = new SwipeMenuCreator() {

        @Override
        public void create(SwipeMenu menu) {
            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(
                    getApplicationContext());
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.parseColor("#F45557")));
            // set item width
            deleteItem.setWidth(150);
            deleteItem.setIcon(R.drawable.delete_icon);
            deleteItem.setTitleColor(Color.WHITE);

            // add to menu
            menu.addMenuItem(deleteItem);
        }
    };
}