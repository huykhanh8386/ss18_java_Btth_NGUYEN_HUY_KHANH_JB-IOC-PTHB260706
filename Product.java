package Btth;

import java.time.LocalDate;

public class Product {

    private int id;
    private String name;
    private double price;
    private String title;
    private LocalDate created;
    private String catalog;
    private boolean status;

    public Product() {
    }

    public Product(int id, String name,
                   double price, String title,
                   LocalDate created,
                   String catalog,
                   boolean status) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.title = title;
        this.created = created;
        this.catalog = catalog;
        this.status = status;
    }

    public Product(String name,
                   double price,
                   String title,
                   LocalDate created,
                   String catalog,
                   boolean status) {

        this.name = name;
        this.price = price;
        this.title = title;
        this.created = created;
        this.catalog = catalog;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreated() {
        return created;
    }

    public String getCatalog() {
        return catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id
                + " | " + name
                + " | " + price
                + " | " + title
                + " | " + created
                + " | " + catalog
                + " | " + status;
    }
}