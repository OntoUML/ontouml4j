package org.ontouml.ontouml4j.serialization.shape;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.serialization.OntoumlElementSerializer;
import org.ontouml.ontouml4j.serialization.Serializer;
import org.ontouml.ontouml4j.shape.RectangularShape;

public class RectangularShapeSerializer extends JsonSerializer<RectangularShape> {
  public static void serializeFields(RectangularShape shape, JsonGenerator jsonGen)
      throws IOException {
    OntoumlElementSerializer.serializeFields(shape, jsonGen);
    Serializer.writeNullableOjectField("topLeft", shape.getTopLeft(), jsonGen);
    Serializer.writeNullableNumberField("width", shape.getWidth(), jsonGen);
    Serializer.writeNullableNumberField("height", shape.getHeight(), jsonGen);
  }

  @Override
  public void serialize(
      RectangularShape value, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(value, jsonGen);
    jsonGen.writeEndObject();
  }
}
