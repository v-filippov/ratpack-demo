package ratpack.demo.app.model;

/**
 * Created by Vladimir_Filippov on 4/13/2016.
 */
final public class Product {
    private final long id;
    private final String name;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
