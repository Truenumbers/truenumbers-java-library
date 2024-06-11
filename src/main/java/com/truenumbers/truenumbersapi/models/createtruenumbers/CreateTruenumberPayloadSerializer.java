package com.truenumbers.truenumbersapi.models.createtruenumbers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CreateTruenumberPayloadSerializer extends StdSerializer<CreateTruenumberPayload> {
    public CreateTruenumberPayloadSerializer() {
        this(null);
    }

    public CreateTruenumberPayloadSerializer(Class<CreateTruenumberPayload> t) {
        super(t);
    }

    @Override
    public void serialize(
            CreateTruenumberPayload payload, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("subject", payload.getSubject());
        jgen.writeStringField("property", payload.getProperty());

        if (payload.getTags() != null) {
            jgen.writeArrayFieldStart("tags");
            for (String tag : payload.getTags()) {
                jgen.writeString(tag);
            }
            jgen.writeEndArray();
        }

        if (payload.getValue() != null) {
            jgen.writeStringField("value", payload.getValue());
        }
        if (payload.getValuePayload() != null) {
            jgen.writeObjectField("value", payload.getValuePayload());
        }

        jgen.writeEndObject();
    }

}
