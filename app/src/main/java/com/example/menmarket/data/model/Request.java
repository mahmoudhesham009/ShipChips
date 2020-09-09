package com.example.menmarket.data.model;

import java.util.ArrayList;

public class Request {
    ArrayList <String> names=new ArrayList<>();
    ArrayList <String> num=new ArrayList<>();
    String totalPrice;
    String phone;
    String address;
    String longitude, latitude;
    String orderTime;

    public Request() {
    }

    public Request(ArrayList<String> names, ArrayList<String> num, String totalPrice, String phone, String address, String longitude, String latitude,String orderTime) {
        this.names = names;
        this.num = num;
        this.totalPrice = totalPrice;
        this.phone = phone;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.orderTime=orderTime;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public ArrayList<String> getNum() {
        return num;
    }

    public void setNum(ArrayList<String> num) {
        this.num = num;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
