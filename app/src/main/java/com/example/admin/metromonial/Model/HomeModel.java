package com.example.admin.metromonial.Model;

/**
 * Created by Admin on 11/27/2017.
 */

public class HomeModel {

    public HomeModel(String s, int l) {
    }

    public int getStar_image() {
        return star_image;
    }

    public void setStar_image(int star_image) {
        this.star_image = star_image;
    }

    public int getCross_image() {
        return cross_image;
    }

    public void setCross_image(int cross_image) {
        this.cross_image = cross_image;
    }

    public int getTick_image() {
        return tick_image;
    }

    public void setTick_image(int tick_image) {
        this.tick_image = tick_image;
    }

    public int getEmail_image() {

        return email_image;
    }

    public void setEmail_image(int email_image) {

        this.email_image = email_image;
    }

    public int getOnlineimage()
    {
        return onlineimage;
    }

    public void setOnlineimage(int onlineimage) {
        this.onlineimage = onlineimage;
    }

    public int getUser_image() {

        return user_image;
    }

    public void setUser_image(int user_image) {
        this.user_image = user_image;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUserdetaols() {
        return userdetaols;
    }

    public void setUserdetaols(String userdetaols) {
        this.userdetaols = userdetaols;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getOnlinrtxt() {
        return onlinrtxt;
    }

    public void setOnlinrtxt(String onlinrtxt) {
        this.onlinrtxt = onlinrtxt;
    }

    int star_image,cross_image,tick_image,email_image,onlineimage,user_image;
    String username, userdetaols, useraddress,onlinrtxt;
}
