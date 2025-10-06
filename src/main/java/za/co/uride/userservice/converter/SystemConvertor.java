package za.co.uride.userservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import za.co.uride.userservice.enums.ESystem;

import java.util.Arrays;
import java.util.Optional;

@Converter(autoApply = true)
public class SystemConvertor implements AttributeConverter<ESystem, String> {
    @Override
    public String convertToDatabaseColumn(ESystem attribute) {
        return attribute.getValue();
    }

    @Override
    public ESystem convertToEntityAttribute(String dbData) {
        Optional<ESystem> optionalSystem = Arrays.stream(ESystem.values()).filter(system -> system.getValue().equalsIgnoreCase(dbData)).findFirst();
        return optionalSystem.get();
    }
}
