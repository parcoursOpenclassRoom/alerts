package com.safetynet.alerts.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.safetynet.alerts.Entity.Person;

import java.io.IOException;
import java.lang.reflect.Field;

public class PersonSerializer extends StdSerializer<Person> {

    public PersonSerializer() {
        this(null);
    }

    public PersonSerializer(Class<Person> t) {
        super(t);
    }

    @Override
    public void serialize(
        Person person, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        Field[] fields = person.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if(field.getName() != "address")
                    jsonGenerator.writeObjectField(field.getName(), field.get(person));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        jsonGenerator.writeEndObject();
    }

}