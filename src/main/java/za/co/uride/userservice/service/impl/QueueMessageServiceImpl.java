package za.co.uride.userservice.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import za.co.uride.userservice.service.QueueMessageService;

@Service
@RequiredArgsConstructor
public class QueueMessageServiceImpl implements QueueMessageService {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gsonMapper;

    @Override
    public void sendMessage(final String topic, final String vHost, final Object message) {
        try {
            SimpleResourceHolder.bind(rabbitTemplate.getConnectionFactory(), vHost);
            rabbitTemplate.convertAndSend(topic, gsonMapper.toJson(message));
        } catch (Exception ex) {
            throw ex;
        } finally {
            SimpleResourceHolder.unbind(rabbitTemplate.getConnectionFactory());
        }
    }

}
