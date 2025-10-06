package za.co.uride.userservice.queue;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import za.co.uride.userservice.dto.ContactDto;
import za.co.uride.userservice.dto.NotificationContactReqDto;
import za.co.uride.userservice.dto.NotificationContactRespDto;
import za.co.uride.userservice.dto.UserAuthority;
import za.co.uride.userservice.dto.UserDto;
import za.co.uride.userservice.enums.EContactType;
import za.co.uride.userservice.enums.ERabbitVirtualHost;
import za.co.uride.userservice.security.JwtAuthenticationToken;
import za.co.uride.userservice.service.ContactService;
import za.co.uride.userservice.service.QueueMessageService;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ContactConsumer {
    @Value("${queue.contact-resp}")
    private String contactRespQueue;
    private final ContactService contactService;
    private final QueueMessageService queueMessageService;
    private final Gson gsonMapper;

    @Transactional
    @RabbitListener(queues = "contact-req", containerFactory = "userVirtualHostListenerContainerFactory")
    public void consumeContactMessage(String message) {
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(List.of(new UserAuthority("edit-entity-user"), new UserAuthority("edit-entity")), "entity-driver-consumer", -1);
        jwtAuthenticationToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);
        NotificationContactReqDto notificationContactReqDto = gsonMapper.fromJson(message, NotificationContactReqDto.class);
        ContactDto contactDto = contactService.findByUser(UserDto.builder().id(notificationContactReqDto.getUserId()).build());
        NotificationContactRespDto.NotificationContactRespDtoBuilder contactRespDtoBuilder = NotificationContactRespDto.builder()
                .notificationId(notificationContactReqDto.getNotificationId())
                .contactName(contactDto.getUser().getName());
        if (notificationContactReqDto.getContactType().equals(EContactType.FCM_TOKEN)) {
            contactRespDtoBuilder.contact(contactDto.getFcmToken());
        } else if (notificationContactReqDto.getContactType().equals(EContactType.CELLPHONE)) {
            contactRespDtoBuilder.contact(contactDto.getCellphoneNumber());
        } else if (notificationContactReqDto.getContactType().equals(EContactType.EMAIL)) {
            contactRespDtoBuilder.contact(contactDto.getEmailAddress());
        }
        queueMessageService.sendMessage(contactRespQueue, ERabbitVirtualHost.USER.getVirtualHost(), gsonMapper.toJson(contactRespDtoBuilder.build()));
    }

}
