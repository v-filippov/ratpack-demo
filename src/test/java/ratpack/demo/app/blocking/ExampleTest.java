package ratpack.demo.app.blocking;

import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import java.util.Collections;

import static groovy.util.GroovyTestCase.assertEquals;


/**
 * Created by Vladimir_Filippov on 3/30/2016.
 */
public class ExampleTest {
    public static void main(String... args) throws Exception {
        System.out.println("Testing blocking operation");
        HandlingResult result = RequestFixture.handle(new DeletingHandler(), fixture -> fixture
                .pathBinding(Collections.singletonMap("days", "10"))
                .registry(r -> r.add(DataStore.class, days -> days))
        );

        assertEquals("10 records deleted", result.rendered(String.class));
    }
}
