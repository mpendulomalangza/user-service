package za.co.uride.userservice.config;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitListenerFactory {
    @Bean
    public SimpleMessageListenerContainer userContainerFactory(UserVirtualHost userVirtualHost) {
        SimpleMessageListenerContainer factory = new SimpleMessageListenerContainer();
        factory.setConnectionFactory(userVirtualHost.connectionFactory());
        factory.setAutoStartup(true);
        return factory;
    }

    @Bean
    public SimpleMessageListenerContainer notificationContainerFactory(
            NotificationVirtualHost notificationVirtualHost) {
        SimpleMessageListenerContainer factory = new SimpleMessageListenerContainer();
        factory.setConnectionFactory(notificationVirtualHost.connectionFactory());
        factory.setAutoStartup(true);
        return factory;
    }

    @Bean
    public SimpleMessageListenerContainer driverContainerFactory(
            DriverVirtualHost driverVirtualHost) {
        SimpleMessageListenerContainer factory = new SimpleMessageListenerContainer();
        factory.setConnectionFactory(driverVirtualHost.connectionFactory());
        factory.setAutoStartup(true);
        return factory;
    }
}
