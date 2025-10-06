package za.co.uride.userservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import za.co.uride.userservice.enums.EUserType;

import java.util.Arrays;

@Converter(autoApply = true)
public class UserTypeConvertor implements AttributeConverter<EUserType, String> {
    @Override
    public String convertToDatabaseColumn(EUserType attribute) {
        return attribute.getValue();
    }

    @Override
    public EUserType convertToEntityAttribute(String dbData) {
        return Arrays.stream(EUserType.values()).filter(userType -> userType.getValue().equalsIgnoreCase(dbData)).findFirst().get();
    }
}
