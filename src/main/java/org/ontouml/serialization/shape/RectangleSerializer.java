package org.ontouml.serialization.shape;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.serialization.OntoumlElementSerializer;
import org.ontouml.shape.Rectangle;

public class RectangleSerializer extends JsonSerializer<Rectangle> {

  public static void serializeFields(Rectangle shape, JsonGenerator jsonGen) throws IOException {
    OntoumlElementSerializer.serializeFields(shape, jsonGen);
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
