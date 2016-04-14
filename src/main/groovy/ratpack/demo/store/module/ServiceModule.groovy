package ratpack.demo.store.module

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import ratpack.demo.store.dao.ProductDAO
import ratpack.demo.store.service.ProductServiceImpl
import ratpack.demo.store.service.api.ProductService

/**
 * Created by Vladimir_Filippov on 4/14/2016.
 */
class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ProductDAO.class).in(Scopes.SINGLETON)
        bind(ProductService.class).to(ProductServiceImpl.class).in(Scopes.SINGLETON)
    }
}
