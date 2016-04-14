package ratpack.demo.app.service.api;

import ratpack.demo.app.model.Product;

import java.util.List;

/**
 * Created by Vladimir_Filippov on 4/13/2016.
 */
public interface ProductService {
    List<Product> findAllProducts();
}
