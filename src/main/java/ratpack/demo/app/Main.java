package ratpack.demo.app;

import com.google.common.reflect.TypeToken;
import com.zaxxer.hikari.HikariConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.demo.app.chain.product.ProductChain;
import ratpack.demo.app.handler.HandlerInsertExample;
import ratpack.demo.app.module.PresentationModule;
import ratpack.demo.app.module.ServiceModule;
import ratpack.demo.app.spring.conf.SpringConfig;
import ratpack.func.Function;
import ratpack.groovy.sql.SqlModule;
import ratpack.guice.Guice;
import ratpack.handling.RequestLogger;
import ratpack.hikari.HikariModule;
import ratpack.registry.Registry;
import ratpack.registry.RegistryBacking;
import ratpack.registry.RegistryBuilder;
import ratpack.registry.RegistrySpec;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

import static ratpack.spring.Spring.spring;

/**
 * Created by Vladimir_Filippov on 3/23/2016.
 */


public class Main {
    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        final ServerConfig serverConfig = ServerConfig
                .builder()
                .baseDir(BaseDir.find())
                .props("postgres.properties")
                .require("/postgres", HikariConfig.class)
                .build();

        final Registry springRegistry = spring(SpringConfig.class);
        final Function<Registry, Registry> registry =
                Guice.registry(r -> {
                    r.moduleConfig(HikariModule.class, serverConfig.get(HikariConfig.class));
                    r.module(SqlModule.class);
                    r.module(ServiceModule.class);
                    r.module(PresentationModule.class);
                });

        Registry r = Registry.empty();
        registry.apply(r.join(springRegistry));
        RatpackServer.start(server -> server
                        .registry(registry)
                        .serverConfig(serverConfig)
                        .handlers(chain -> chain
                                        .all(RequestLogger.ncsa())

                                        .prefix("product", ProductChain.class)

                                        .prefix("insert",
                                                insertExampleChain ->
                                                        insertExampleChain
                                                                .get(ctx -> {
                                                                    ctx.insert(new HandlerInsertExample(),
                                                                            h -> h.render("Insert"));
                                                                }))
                                        .get(ctx -> {
                                            LOGGER.info("bean: {}", ctx.get(String.class));
                                            ctx.render("Hello World!!");
                                        })
                                        .get("/:name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!"))
//                                .get("/insert", ctx -> {
//                                    ctx.insert(new HandlerInsertExample());
//                                    //LOGGER.info("I'm about to finish");
//                                    //ctx.render("The end");
//                                })

                        )
        );
    }
}