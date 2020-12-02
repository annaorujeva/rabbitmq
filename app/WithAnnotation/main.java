package app.WithAnnotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(config.class)
public class main {
    public static void main(String[] args){
        SpringApplication.run(main.class, args);
    }
}
