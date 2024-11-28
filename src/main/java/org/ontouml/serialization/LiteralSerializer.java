package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.Literal;

public class LiteralSerializer extends JsonSerializer<Literal> {

  public static void serializeFields(Literal literal, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeStringField("type", "Literal");
    ModelElementSerializer.serializeFields(literal, jsonGen);
  }

  @Override
  public void serialize(Literal literal, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(literal, jsonGen);
    jsonGen.writeEndObject();
  }
}
