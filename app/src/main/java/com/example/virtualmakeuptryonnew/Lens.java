package com.example.virtualmakeuptryonnew;

public class Lens {

    Integer p_id;
    String name;
    String price;
    String image;

    public Lens() {
    }

    public Lens(int p_id, String name, String price, String image) {
        this.p_id = p_id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getP_id() {
        return p_id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }
}
