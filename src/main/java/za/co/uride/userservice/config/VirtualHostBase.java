package za.co.uride.userservice.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Abstract class for virtual host to rabbit
 */
public abstract class VirtualHostBase {
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private transient String password;
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;


    protected abstract String getVHost();

    public ConnectionFactory connectionFactory() {
        var connFactory = new CachingConnectionFactory(host, port);
        connFactory.setVirtualHost(getVHost());
        connFactory.setUsername(username);
        connFactory.setPassword(password);
        return connFactory;
    }
}

