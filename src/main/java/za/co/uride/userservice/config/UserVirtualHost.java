package za.co.uride.userservice.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.co.uride.userservice.enums.ERabbitVirtualHost;

@Configuration
public class UserVirtualHost extends VirtualHostBase {
    @Override
    public String getVHost() {
        return ERabbitVirtualHost.USER.getVirtualHost();
    }

    @Bean
    public ConnectionFactory userConnectionFactory() {
        return connectionFactory();
    }
}
