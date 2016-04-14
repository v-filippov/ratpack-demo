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
import ratpack.server.BaseDir
import ratpack.server.Service
import ratpack.server.StartEvent
import ratpack.rx.RxRatpack

import static ratpack.groovy.Groovy.ratpack

ratpack {
    final Logger LOG = LoggerFactory.getLogger("server")

    serverConfig {
        baseDir(BaseDir.find())
        props("postgres.properties")
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