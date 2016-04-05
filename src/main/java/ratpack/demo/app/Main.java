package ratpack.demo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.demo.app.handler.HandlerInsertExample;
import ratpack.handling.RequestLogger;
import ratpack.server.RatpackServer;

/**
 * Created by Vladimir_Filippov on 3/23/2016.
 */


public class Main {
    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {


        RatpackServer.start(server -> server
                        // .serverConfig(c -> c.baseDir((BaseDir.find())))
                        .handlers(chain -> chain
                                        .all(RequestLogger.ncsa())
                                        .prefix("insert",
                                                insertExampleChain ->
                                                        insertExampleChain
                                                                .get(ctx -> {
                                                                    ctx.insert(new HandlerInsertExample(),
                                                                            h -> h.render("Insert"));
                                                                }))
                                        .get(ctx -> ctx.render("Hello World!!"))
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