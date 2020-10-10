package com.gaya_second.android_pro.Model;

import java.io.Serializable;

public class Delivery implements Serializable {
     private String Id;
     private String contactName;
     private String phoneNumber;
     private String mobileNumber;
     private String address;
     private String district;
     private String postal;

     public String getId() {
         return Id;
     }

     public void setId(String id) {
         Id = id;
     }

     public String getContactName() {
         return contactName;
     }

     public void setContactName(String contactName) {
         this.contactName = contactName;
     }

     public String getPhoneNumber() {
         return phoneNumber;
     }

     public void setPhoneNumber(String phoneNumber) {
         this.phoneNumber = phoneNumber;
     }

     public String getMobileNumber() {
         return mobileNumber;
     }

     public void setMobileNumber(String mobileNumber) {
         this.mobileNumber = mobileNumber;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }

     public String getDistrict() {
         return district;
     }

     public void setDistrict(String district) {
         this.district = district;
     }

     public String getPostal() {
         return postal;
     }

     public void setPostal(String postal) {
         this.postal = postal;
     }
 }
