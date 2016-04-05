package ratpack.demo.app.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by Vladimir_Filippov on 3/28/2016.
 */
public class PrintThenNextHandler implements Handler {
    private final String message;
    private final static Logger LOGGER = LoggerFactory.getLogger(PrintThenNextHandler.class);


    public PrintThenNextHandler(String message) {
        this.message = message;
    }
    @Override
    public void handle(Context ctx) throws Exception {
        LOGGER.info(message);
        ctx.next();
    }
}
