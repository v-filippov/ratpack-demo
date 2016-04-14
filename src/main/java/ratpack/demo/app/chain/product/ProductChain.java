package ratpack.demo.app.chain.product;

import com.google.inject.Inject;
import ratpack.demo.app.model.Product;
import ratpack.demo.app.service.ProductServiceImpl;
import ratpack.func.Action;
import ratpack.handling.Chain;

import java.util.List;

import static ratpack.jackson.Jackson.json;

/**
 * Created by Vladimir_Filippov on 4/13/2016.
 */
public class ProductChain implements Action<Chain> {
    private ProductServiceImpl productService;

    @Inject
    public ProductChain(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Override
    public void execute(Chain chain) throws Exception {
        chain.get(ctx -> {
            List<Product> products = productService.findAllProducts();
            ctx.render(json(products));
        });
    }
}
