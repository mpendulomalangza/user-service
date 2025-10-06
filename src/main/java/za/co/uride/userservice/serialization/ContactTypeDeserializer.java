package za.co.uride.userservice.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import za.co.uride.userservice.enums.EContactType;

import java.lang.reflect.Type;

public class ContactTypeDeserializer implements JsonDeserializer<EContactType> {
    @Override
    public EContactType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return EContactType.valueOf(json.getAsString());
    }
}


