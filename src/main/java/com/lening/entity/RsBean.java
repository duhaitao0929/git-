package com.lening.entity;


public class RsBean {
//酒水和香烟的 实体类 在一块
    private Integer id;
    private String cardno;
    private String madetime;
    private String address;
    private String price;
    private String vol;
    private String name;

    public String getMadetime() {
        return madetime;
    }

    public void setMadetime(String madetime) {
        this.madetime = madetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }
}
