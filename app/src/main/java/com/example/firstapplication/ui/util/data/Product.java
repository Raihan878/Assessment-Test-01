package com.example.firstapplication.ui.util.data;

public class Product {

    private int id;
    private String name;
    private String catagory;
    private String description;
    private int price;
    public Product(){
    }
    public Product(int id, String name, String catagory, String description, int price) {
        this.id = id;
        this.name = name;
        this.catagory = catagory;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", catagory='" + catagory + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
