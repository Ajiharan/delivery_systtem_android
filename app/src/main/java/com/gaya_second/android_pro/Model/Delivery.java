package com.gaya_second.android_pro.Model;

 public class Delivery {
     private String contactName;
     private int phoneNumber;
     private int mobileNumber;
     private String address;
     private String district;
     private int postal;

     public String getContactName() {
         return contactName;
     }

     public void setContactName(String contactName) {
         this.contactName = contactName;
     }

     public int getPhoneNumber() {
         return phoneNumber;
     }

     public void setPhoneNumber(int phoneNumber) {
         this.phoneNumber = phoneNumber;
     }

     public int getMobileNumber() {
         return mobileNumber;
     }

     public void setMobileNumber(int mobileNumber) {
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

     public int getPostal() {
         return postal;
     }

     public void setPostal(int postal) {
         this.postal = postal;
     }
 }
