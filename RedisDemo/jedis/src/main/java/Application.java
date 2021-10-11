import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author zcc
 * @Date 2021/8/6 15:06
 * @Describe
 */
@SpringBootApplication
@ComponentScan(value = "com.zcc.jedis.controller")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
