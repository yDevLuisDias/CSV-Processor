package com.model;

public class ItemsEntity {

    private long id;
    private String name;
    private Double price;
    private String category;
    private Integer quantity;

    public ItemsEntity(long id, String name, Double price, String category, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String toLine() {
        return this.id + "," + this.name + "," + this.price + "," + this.category + "," + this.quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id + '\n' +
                ", name='" + name + '\n' +
                ", price=" + price + '\n' +
                ", category='" + category + '\n' +
                ", quantity=" + quantity + '\n' +
                '}';
    }
}
