
package entity;

public class Productsizes {
    private int product_size_id;
    private String product_id;
    private String product_name;
    private String size;
    private int stock_quantity;

    public Productsizes() {
    }

    public Productsizes(int product_size_id, String product_id, String product_name, String size, int stock_quantity) {
        this.product_size_id = product_size_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.size = size;
        this.stock_quantity = stock_quantity;
    }

    public int getProduct_size_id() {
        return product_size_id;
    }

    public void setProduct_size_id(int product_size_id) {
        this.product_size_id = product_size_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    @Override
    public String toString() {
        return "Productsizes{" + "product_size_id=" + product_size_id + ", product_id=" + product_id + ", product_name=" + product_name + ", size=" + size + ", stock_quantity=" + stock_quantity + '}';
    }

    
}
