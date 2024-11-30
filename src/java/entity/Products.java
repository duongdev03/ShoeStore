package entity;

public class Products {
    private int product_id;
    private String product_name;
    private String description;
    private double price;
    private String image_url;
    private String category_id;

    public Products() {
    }

    public Products(int product_id, String product_name, String description, double price, String image_url, String category_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
        this.category_id = category_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Products{" + "product_id=" + product_id + ", product_name=" + product_name + ", description=" + description + ", price=" + price + ", image_url=" + image_url + ", category_id=" + category_id + '}';
    }
    
    
}
