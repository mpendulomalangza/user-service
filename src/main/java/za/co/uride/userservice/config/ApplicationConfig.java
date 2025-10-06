package za.co.uride.userservice.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import za.co.uride.userservice.enums.EConsentType;
import za.co.uride.userservice.serialization.ContactTypeDeserializer;
import za.co.uride.userservice.serialization.LocalDateDeserializer;
import za.co.uride.userservice.serialization.LocalDateSerializer;
import za.co.uride.userservice.serialization.LocalDateTimeDeserializer;
import za.co.uride.userservice.serialization.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
@EnableJpaAuditing
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gsonMapper() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(EConsentType.class, new ContactTypeDeserializer());
        return gsonBuilder.create();
    }

    /**
     * @return {@link AuditorAware}  for setting of audit created and modified by user on entities
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
