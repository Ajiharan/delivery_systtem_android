package com.gaya_second.android_pro.Adaptors;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.gaya_second.android_pro.Model.Delivery;
import com.gaya_second.android_pro.R;

import java.util.ArrayList;
import java.util.List;
public class DeliveryAdapter extends ArrayAdapter<Delivery> {
    Activity context;
    List<Delivery> items;

    public DeliveryAdapter(@NonNull Activity context, ArrayList<Delivery> dataArrayList) {
        super(context, 0, dataArrayList);
        this.context=context;
        this.items=dataArrayList;
    }

    private class ViewHolder {

        TextView contactName;
        TextView phoneNo;
        TextView telephoneNo;
        TextView deliveryAddress;
        TextView district;
        TextView postal;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DeliveryAdapter.ViewHolder holder = null;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(
                    R.layout.delivery_list, parent, false);

            holder = new DeliveryAdapter.ViewHolder();
            holder.contactName = (TextView) convertView.findViewById(R.id.txt_contactName);

            holder.phoneNo = (TextView) convertView.findViewById(R.id.txt_phoneNo);
            holder.telephoneNo=(TextView)convertView.findViewById(R.id.txt_telphoneNo);
            holder.deliveryAddress = (TextView) convertView.findViewById(R.id.txt_address);
            holder.district=(TextView)convertView.findViewById(R.id.txt_district);
            holder.postal=(TextView)convertView.findViewById(R.id.txt_postal);
            convertView.setTag(holder);

        } else {
            holder = (DeliveryAdapter.ViewHolder) convertView.getTag();
        }

        Delivery delivery = items.get(position);

        holder.contactName.setText(delivery.getContactName());
        holder.phoneNo.setText(String.valueOf(delivery.getMobileNumber()));
        holder.telephoneNo.setText(String.valueOf(delivery.getPhoneNumber()));
        holder.postal.setText(String.valueOf(delivery.getPostal()));
        holder.deliveryAddress.setText(delivery.getAddress());
        holder.district.setText(delivery.getDistrict());

        return convertView;

    }

}
