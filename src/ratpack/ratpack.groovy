//@Grapes([
//        @Grab('io.ratpack:ratpack-groovy:1.2.0'),
//        @Grab('org.slf4j:slf4j-simple:1.7.12')
//])
import com.zaxxer.hikari.HikariConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ratpack.demo.store.chain.product.ProductChain
import ratpack.demo.store.module.PresentationModule
import ratpack.demo.store.module.ServiceModule
import ratpack.groovy.sql.SqlModule
import ratpack.hikari.HikariModule
import ratpack.rx.RxRatpack
import ratpack.server.BaseDir
import ratpack.server.Service
import ratpack.server.StartEvent

import static ratpack.groovy.Groovy.ratpack

ratpack {
    final Logger LOG = LoggerFactory.getLogger("server")

    serverConfig {
        baseDir(BaseDir.find())
        def jdbcUrl = System.getenv('JDBC_DATABASE_URL')
        if (jdbcUrl) {
            LOG.info('JDBC_DATABASE_URL is provided, using it to configure the datasource')
            props(['postgres.jdbcUrl': jdbcUrl])
        } else {
            LOG.info('Using postgres.properties to configure the datasource')
            props("postgres.properties")
        }
        require("/postgres", HikariConfig)
    }

    bindings {
        moduleConfig(HikariModule, HikariConfig)
        module SqlModule
        module ServiceModule
        module PresentationModule
        bindInstance Service, new Service() {
            @Override
            void onStart(StartEvent event) throws Exception {
                LOG.info "Initializing RX"
                RxRatpack.initialize()
            }
        }
    }

    handlers {
        prefix('product', ProductChain)
    }
}