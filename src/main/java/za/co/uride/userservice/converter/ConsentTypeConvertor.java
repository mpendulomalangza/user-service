package za.co.uride.userservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import za.co.uride.userservice.enums.EConsentType;

import java.util.Arrays;
import java.util.Optional;

@Converter(autoApply = true)
public class ConsentTypeConvertor implements AttributeConverter<EConsentType, String> {
    @Override
    public String convertToDatabaseColumn(EConsentType attribute) {
        return attribute.getCode();
    }

    @Override
    public EConsentType convertToEntityAttribute(String dbData) {
        Optional<EConsentType> optionalSystem = Arrays.stream(EConsentType.values()).filter(consentType -> consentType.getCode().equalsIgnoreCase(dbData)).findFirst();
        return optionalSystem.get();
    }
}
