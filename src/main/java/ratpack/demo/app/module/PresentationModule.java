package ratpack.demo.app.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ratpack.demo.app.chain.product.ProductChain;

/**
 * Created by Vladimir_Filippov on 4/13/2016.
 */
public class PresentationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ProductChain.class).in(Scopes.SINGLETON);
    }
}
