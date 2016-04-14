package ratpack.demo.store.service

import com.google.inject.Inject
import ratpack.demo.store.dao.ProductDAO
import ratpack.demo.store.model.Product
import ratpack.demo.store.service.api.ProductService

/**
 * Created by Vladimir_Filippov on 4/14/2016.
 */
class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO

    @Inject
    ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO
    }

    @Override
    List<Product> findAllProducts() {
        productDAO.findAll()
    }
}
