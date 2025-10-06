package za.co.uride.userservice.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitListenerFactory {
    @Bean
    public SimpleRabbitListenerContainerFactory userVirtualHostListenerContainerFactory(UserVirtualHost userVirtualHost) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(userVirtualHost.connectionFactory());
        return factory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory notificationVirtualHostListenerContainerFactory(
            NotificationVirtualHost notificationVirtualHost) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(notificationVirtualHost.connectionFactory());
        return factory;
    }
}
