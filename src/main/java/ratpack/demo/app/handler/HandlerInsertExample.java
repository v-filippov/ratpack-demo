package ratpack.demo.app.handler;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by Vladimir_Filippov on 3/28/2016.
 */
public class HandlerInsertExample implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.insert(
                new PrintThenNextHandler("a"),
                new PrintThenNextHandler("b"),
                new PrintThenNextHandler("c")
        );
    }

}
