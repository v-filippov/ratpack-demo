package ratpack.demo.app.service;

import com.google.inject.Inject;
import ratpack.demo.app.dao.ProductDAO;
import ratpack.demo.app.model.Product;
import ratpack.demo.app.service.api.ProductService;

import java.util.List;

/**
 * Created by Vladimir_Filippov on 4/13/2016.
 */
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Inject
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> findAllProducts() {
        return productDAO.findAll();
    }
}
