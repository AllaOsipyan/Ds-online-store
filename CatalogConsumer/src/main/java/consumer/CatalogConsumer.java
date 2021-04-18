package consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
@Configuration
@SpringBootApplication
public class CatalogConsumer {
    public static void main(String[] args) {
        SpringApplication.run(CatalogConsumer.class, args);
    }
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("links");
    }

    @Bean
    public Queue link() {
        return new Queue("link");
    }

    @Bean
    public Binding binding(FanoutExchange fanout,
                            Queue link) {
        return BindingBuilder.bind(link).to(fanout);
    }

    @Bean
    public Consumer receiver() {
        return new Consumer();
    }
}
