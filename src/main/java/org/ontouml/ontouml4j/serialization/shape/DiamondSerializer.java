package org.ontouml.ontouml4j.serialization.shape;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.serialization.Serializer;
import org.ontouml.ontouml4j.shape.Diamond;

public class DiamondSerializer extends JsonSerializer<Diamond> {
  public static void serializeFields(Diamond shape, JsonGenerator jsonGen) throws IOException {
    Serializer.writeNullableStringField("type", "Diamond", jsonGen);
    RectangularShapeSerializer.serializeFields(shape, jsonGen);
  }

  @Override
  public void serialize(Diamond shape, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(shape, jsonGen);
    jsonGen.writeEndObject();
  }
}
