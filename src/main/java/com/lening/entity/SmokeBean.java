package com.lening.entity;



public class SmokeBean {

    private Integer id;
    private String cardno;
    private String madedate;
    private String address;
    private String price;
    private String name;

    public SmokeBean() {
    }

    public SmokeBean(String cardno, String madedate, String address, String price, String name) {
        this.cardno = cardno;
        this.madedate = madedate;
        this.address = address;
        this.price = price;
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

    public String getMadedate() {
        return madedate;
    }

    public void setMadedate(String madedate) {
        this.madedate = madedate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
