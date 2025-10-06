package za.co.uride.userservice.service;

public interface QueueMessageService {
    void sendMessage(final String topic, final String vHosts, final Object message);
}
