package ratpack.demo.app.spring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vladimir_Filippov on 4/13/2016.
 */
@Configuration
public class SpringConfig {
    @Bean(name = "myBean")
    public String myBean() {
        return "myBean";
    }
}
