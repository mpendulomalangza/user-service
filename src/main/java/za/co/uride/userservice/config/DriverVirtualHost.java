package za.co.uride.userservice.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.co.uride.userservice.enums.ERabbitVirtualHost;

@Configuration
public class DriverVirtualHost extends VirtualHostBase {
    @Override
    public String getVHost() {
        return ERabbitVirtualHost.DRIVER.getVirtualHost();
    }

    @Bean
    public ConnectionFactory driverConnectionFactory() {
        return connectionFactory();
    }
}
