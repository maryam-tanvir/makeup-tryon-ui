package com.example.virtualmakeuptryonnew;

public class Mascara {

    Integer p_id;
    String name;
    String price;
    String image;

    public Mascara() {
    }

    public Mascara(Integer p_id, String name, String price, String image) {
        this.p_id = p_id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Integer getP_id() {
        return p_id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }
}
