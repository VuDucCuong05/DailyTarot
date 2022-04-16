package com.example.fingerprintscandailytarot.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity( tableName = "language")
public class Language {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int image;
    private int name;
    private boolean check;
    private String ma;

    public Language(int image, int name, boolean check, String ma) {
        this.image = image;
        this.name = name;
        this.check = check;
        this.ma = ma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }
}
