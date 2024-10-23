package springboot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
        }

        System.out.println(beanDefinitionNames.length);
    }

    @Bean() // By default, Bean objects are Singletons
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Can be changed from singleton to other
//    @SessionScope
//    @ApplicationScope
    public String redBean() {
        return "Manchester United";
    }

    @Bean
    public String blueBean() {
        return "Chelsea";
    }

    @Bean
    CommandLineRunner commandLineRunner(String redBean , String blueBean) {
        return args -> {
            System.out.println("Hello from CommandLineRunner");
            System.out.println(redBean);
            System.out.println(blueBean);
        };
    }

    @Bean
    CommandLineRunner commandLineRunner2(String redBean , String blueBean) {
        return args -> {
            System.out.println("Hello from CommandLineRunner");
            System.out.println(redBean);
            System.out.println(blueBean);
        };
    }

}
