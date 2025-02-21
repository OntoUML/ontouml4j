package org.ontouml.ontouml4j.serialization.shape;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.serialization.Serializer;
import org.ontouml.ontouml4j.shape.Rectangle;

public class RectangleSerializer extends JsonSerializer<Rectangle> {

  public static void serializeFields(Rectangle shape, JsonGenerator jsonGen) throws IOException {
    Serializer.writeNullableStringField("type", "Rectangle", jsonGen);
    RectangularShapeSerializer.serializeFields(shape, jsonGen);
  }

  @Override
  public void serialize(Rectangle shape, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(shape, jsonGen);
    jsonGen.writeEndObject();
  }
}
