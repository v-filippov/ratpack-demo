//@Grapes([
//        @Grab('io.ratpack:ratpack-groovy:1.2.0'),
//        @Grab('org.slf4j:slf4j-simple:1.7.12')
//])
import org.slf4j.LoggerFactory

import static ratpack.groovy.Groovy.ratpack

ratpack {
    def log = LoggerFactory.getLogger("server")
    serverConfig {
        development true
    }
    handlers {
        prefix("insert") {
            get {
                render "Insert"
            }
        }
        get {
            System.out.println("test")
            log.info("qqq")
            render "Hello World! Gyus!!"
        }
        get("/:name") {
            render "Hello $pathTokens.name!"
        }
        get("/:hello") {
            render "Hello"
        }
    }
}