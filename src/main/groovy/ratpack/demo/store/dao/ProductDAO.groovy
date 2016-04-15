package ratpack.demo.store.dao

import com.google.inject.Inject
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import ratpack.demo.store.model.Product

/**
 * Created by Vladimir_Filippov on 4/14/2016.
 */
class ProductDAO {
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product"
    private static final MAP_PRODUCT = { GroovyRowResult row ->
        new Product(id: row.getProperty('id'), name: row.getProperty('name'))
    }
    private final Sql sql

    @Inject
    ProductDAO(Sql sql) {
        this.sql = sql
    }

    List<Product> findAll() {
        sql.rows(SELECT_ALL_PRODUCTS).collect {MAP_PRODUCT(it)}
    }
}
