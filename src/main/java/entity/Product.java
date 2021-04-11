package entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column
    private String description;

    @Column(name = "fastingItem", nullable = false)
    private boolean fastingItem; // mancare de post

    @Column
    private Integer weight;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Lob
    private String imageURL;

    public Product(){}

    public Product(String name, String category, String description, boolean fastingItem, Integer weight, Integer quantity, Double price, byte[] imageURL) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.category = category;
        this.description = description;
        this.fastingItem = fastingItem;
        this.weight = weight;
        this.quantity = quantity;
        this.price = price;
        //this.imageURL = imageURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFastingItem() {
        return fastingItem;
    }

    public void setFastingItem(Boolean f) {this.fastingItem = f; }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setFastingItem(boolean fastingItem) {
        this.fastingItem = fastingItem;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
