package za.co.uride.userservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import za.co.uride.userservice.enums.EVerificationType;

import java.util.Arrays;
import java.util.Optional;

@Converter(autoApply = true)
public class VerificationTypeConvertor implements AttributeConverter<EVerificationType, String> {
    @Override
    public String convertToDatabaseColumn(EVerificationType attribute) {
        return attribute.getValue();
    }

    @Override
    public EVerificationType convertToEntityAttribute(String dbData) {
        Optional<EVerificationType> optionalSystem = Arrays.stream(EVerificationType.values()).filter(system -> system.getValue().equalsIgnoreCase(dbData)).findFirst();
        return optionalSystem.get();
    }
}
