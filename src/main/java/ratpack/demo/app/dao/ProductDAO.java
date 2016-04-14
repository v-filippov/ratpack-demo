package ratpack.demo.app.dao;

import ratpack.demo.app.model.Product;

import java.util.Collections;
import java.util.List;

/**
 * Created by Vladimir_Filippov on 4/13/2016.
 */
public class ProductDAO {
    private final static String SELECT_ALL_PRODUCTS = "SELECT * FROM PRODUCT";

    public List<Product> findAll() {
        return Collections.singletonList(new Product(1, "test"));
    }
}
