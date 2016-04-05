package ratpack.demo.app.blocking;

import ratpack.exec.Blocking;
import ratpack.handling.Context;
import ratpack.handling.InjectionHandler;

import java.io.IOException;

/**
 * Created by Vladimir_Filippov on 3/30/2016.
 */

public class DeletingHandler extends InjectionHandler {

    void handle(final Context context, final DataStore dataStore) {
        final int days = context.getPathTokens().asInt("days");
        Blocking.get(() -> dataStore.deleteOlderThan(days))
                .then(i -> context.render(i + " records deleted"));
    }

}

// Some API that performs blocking operations
interface DataStore {
    int deleteOlderThan(int days) throws IOException;
}



