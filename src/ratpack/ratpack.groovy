//@Grapes([
//        @Grab('io.ratpack:ratpack-groovy:1.2.0'),
//        @Grab('org.slf4j:slf4j-simple:1.7.12')
//])
import com.zaxxer.hikari.HikariConfig
import org.slf4j.LoggerFactory
import ratpack.demo.store.service.api.ProductService
import ratpack.demo.store.module.ServiceModule
import ratpack.groovy.sql.SqlModule
import ratpack.hikari.HikariModule
import ratpack.server.BaseDir

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
    def log = LoggerFactory.getLogger("server")

    serverConfig {
        baseDir(BaseDir.find())
        props("postgres.properties")
        require("/postgres", HikariConfig)
    }

    bindings {
        moduleConfig(HikariModule, HikariConfig)
        module SqlModule
        module ServiceModule
    }

    handlers {
        prefix('product') {
            get { ProductService productService ->
                def products = productService.findAllProducts()
                render(json(products))
            }
        }

    }
}