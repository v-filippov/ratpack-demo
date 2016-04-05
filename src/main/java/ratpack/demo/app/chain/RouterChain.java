package ratpack.demo.app.chain;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.server.RatpackServer;

/**
 * Created by Vladimir_Filippov on 3/30/2016.
 */
public class RouterChain implements Action<Chain> {
    private final Handler fooHandler = new FooHandler();
    private final Handler barHandler = new BarHandler();

    @Override
    public void execute(Chain chain) throws Exception {
        chain.path("foo", fooHandler);
        chain.path("bar", barHandler);
    }
}

class FooHandler implements Handler {
    public void handle(Context context) {
        context.getResponse().send("foo");
    }
}

class BarHandler implements Handler {
    public void handle(Context context) {
        context.getResponse().send("bar");
    }
}

class ChainExample {

    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
                // .serverConfig(c -> c.baseDir((BaseDir.find())))
                .handlers(new RouterChain()));
    }
}
