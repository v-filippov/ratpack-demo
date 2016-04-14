package ratpack.demo.store.module

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import ratpack.demo.store.chain.product.ProductChain


/**
 * Created by Vladimir_Filippov on 4/14/2016.
 */
class PresentationModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(ProductChain.class).in(Scopes.SINGLETON)
    }
}
