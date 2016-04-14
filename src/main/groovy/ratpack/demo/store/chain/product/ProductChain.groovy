package ratpack.demo.store.chain.product

import ratpack.demo.store.model.Product
import ratpack.demo.store.service.api.ProductService
import ratpack.exec.Blocking
import ratpack.exec.Promise
import ratpack.groovy.handling.GroovyChainAction

import static ratpack.rx.RxRatpack.observe
import static ratpack.jackson.Jackson.json

/**
 * Created by Vladimir_Filippov on 4/14/2016.
 */
class ProductChain extends GroovyChainAction {

    @Override
    void execute() throws Exception {
        get { ProductService productService ->
            def products = []
            Promise<List<Product>> promise = Blocking.get { products = productService.findAllProducts() }
            observe(promise).subscribe {render(json(products))}
        }
    }
}
