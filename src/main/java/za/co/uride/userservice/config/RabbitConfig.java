package za.co.uride.userservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final List<VirtualHostBase> virtualHostConfigurations;

    @Bean
    @Primary
    public SimpleRoutingConnectionFactory routingConnectionFactory() {
        final SimpleRoutingConnectionFactory simpleRoutingConnectionFactory = new SimpleRoutingConnectionFactory();
        final Map<Object, ConnectionFactory> routeMap = virtualHostConfigurations.stream().collect(Collectors.toMap(VirtualHostBase::getVHost, VirtualHostBase::connectionFactory));
        simpleRoutingConnectionFactory.setTargetConnectionFactories(routeMap);
        simpleRoutingConnectionFactory.setDefaultTargetConnectionFactory(routeMap.get("user"));
        return simpleRoutingConnectionFactory;
    }

}
