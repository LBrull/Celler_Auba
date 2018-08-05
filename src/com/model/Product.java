package com.model;

public class Product {

    private String code;
    private String description;
    private String type;
    private String price;

    public Product() {

    }

    public Product(String code, String description, String type, String price) {
        this.code = code;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
