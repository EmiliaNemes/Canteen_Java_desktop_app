package dto;

import javafx.scene.image.ImageView;

public class ProductDto {

    private String id;
    private String name;
    private String category;
    private String description;
    private boolean fastingItem; // mancare de post
    private Integer weight;
    private Integer quantity;
    private Double price;
    private String imageURL;

    private ImageView image; //adaugata pt insert-ul imaginii in tabel

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
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

    public void setFastingItem(String fastingItem) {
        this.fastingItem = fastingItem.equals("Yes");
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", fastingItem=" + fastingItem +
                ", weight=" + weight +
                ", quantity=" + quantity +
                ", price=" + price +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
