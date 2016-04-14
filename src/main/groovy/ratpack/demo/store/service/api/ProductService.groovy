package ratpack.demo.store.service.api

import ratpack.demo.store.model.Product

/**
 * Created by Vladimir_Filippov on 4/14/2016.
 */
interface ProductService {
    List<Product> findAllProducts()
}