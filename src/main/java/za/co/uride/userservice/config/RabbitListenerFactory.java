package za.co.uride.userservice.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitListenerFactory {
    @Bean
    public SimpleMessageListenerContainer userVirtualHostListenerContainerFactory(UserVirtualHost userVirtualHost) {
        SimpleMessageListenerContainer factory = new SimpleMessageListenerContainer();
        factory.setConnectionFactory(userVirtualHost.connectionFactory());
        factory.setAutoStartup(true);
        return factory;
    }

    @Bean
    public SimpleMessageListenerContainer notificationVirtualHostListenerContainerFactory(
            NotificationVirtualHost notificationVirtualHost) {
        SimpleMessageListenerContainer factory = new SimpleMessageListenerContainer();
        factory.setConnectionFactory(notificationVirtualHost.connectionFactory());
        factory.setAutoStartup(true);
        return factory;
    }
}
