package com.example.fingerprintscandailytarot.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "Card")
public class Card implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("data")
    @Expose
    private String contentLove;
    private String contentHealth;
    private String contentCareer;
    private String timeLoading;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentLove() {
        return contentLove;
    }

    public void setContentLove(String contentLove) {
        this.contentLove = contentLove;
    }

    public String getContentHealth() {
        return contentHealth;
    }

    public void setContentHealth(String contentHealth) {
        this.contentHealth = contentHealth;
    }

    public String getContentCareer() {
        return contentCareer;
    }

    public void setContentCareer(String contentCareer) {
        this.contentCareer = contentCareer;
    }

    public String getTimeLoading() {
        return timeLoading;
    }

    public void setTimeLoading(String timeLoading) {
        this.timeLoading = timeLoading;
    }

    public Card() {
    }

    public Card(String contentLove, String contentHealth, String contentCareer, String timeLoading) {
        this.contentLove = contentLove;
        this.contentHealth = contentHealth;
        this.contentCareer = contentCareer;
        this.timeLoading = timeLoading;
    }


    public Card(String contentHealth) {
        this.contentHealth = contentHealth;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", contentLove='" + contentLove + '\'' +
                ", contentHealth='" + contentHealth + '\'' +
                ", contentCareer='" + contentCareer + '\'' +
                ", timeLoading='" + timeLoading + '\'' +
                '}';
    }
}
