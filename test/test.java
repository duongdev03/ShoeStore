
import dao.ProductsizesDAO;

public class test {

    public static void main(String[] args) {
        ProductsizesDAO dao = new ProductsizesDAO();
        dao.getSizesByProductId("6");
        

    }
}
