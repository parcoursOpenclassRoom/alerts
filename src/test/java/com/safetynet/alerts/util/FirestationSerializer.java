package com.safetynet.alerts.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.safetynet.alerts.Entity.Firestation;

import java.io.IOException;
import java.lang.reflect.Field;

public class FirestationSerializer extends StdSerializer<Firestation> {

    public FirestationSerializer() {
        this(null);
    }

    public FirestationSerializer(Class<Firestation> t) {
        super(t);
    }

    @Override
    public void serialize(
            Firestation firestation, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        Field[] fields = firestation.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if(field.getName() != "address")
                    jsonGenerator.writeObjectField(field.getName(), field.get(firestation));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        jsonGenerator.writeEndObject();
    }

}