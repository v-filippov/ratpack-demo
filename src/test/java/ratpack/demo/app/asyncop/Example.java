package ratpack.demo.app.asyncop;

import ratpack.exec.Promise;
import ratpack.test.embed.EmbeddedApp;

import static groovy.util.GroovyTestCase.assertEquals;

/**
 * Created by Vladimir_Filippov on 3/30/2016.
 */
public class Example {
    public static void main(String... args) throws Exception {
        EmbeddedApp.fromHandler(ctx ->
                Promise.of((f) ->
                        new Thread(() -> f.success("hello world")).start()
                ).then(ctx::render)
        ).test(httpClient -> {
            assertEquals("hello world", httpClient.getText());
        });
    }
}
