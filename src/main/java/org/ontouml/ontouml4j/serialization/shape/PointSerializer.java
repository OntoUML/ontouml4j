package org.ontouml.ontouml4j.serialization.shape;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.shape.Point;

public class PointSerializer extends JsonSerializer<Point> {
  public static void serializeFields(Point point, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeNumberField("x", point.getX());
    jsonGen.writeNumberField("y", point.getY());
  }

  @Override
  public void serialize(Point shape, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(shape, jsonGen);
    jsonGen.writeEndObject();
  }
}
