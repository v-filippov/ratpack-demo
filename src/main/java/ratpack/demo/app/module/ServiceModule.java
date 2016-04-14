package ratpack.demo.app.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ratpack.demo.app.dao.ProductDAO;
import ratpack.demo.app.service.ProductServiceImpl;
import ratpack.demo.app.service.api.ProductService;

/**
 * Created by Vladimir_Filippov on 4/13/2016.
 */
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ProductDAO.class).in(Scopes.SINGLETON);
        bind(ProductService.class).to(ProductServiceImpl.class).in(Scopes.SINGLETON);
    }
}
