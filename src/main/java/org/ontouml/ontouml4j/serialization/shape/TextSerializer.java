package org.ontouml.ontouml4j.serialization.shape;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.shape.Text;

public class TextSerializer extends JsonSerializer<Text> {

  public static void serializeFields(Text text, JsonGenerator jsonGen) throws IOException {
    RectangularShapeSerializer.serializeFields(text, jsonGen);
  }

  @Override
  public void serialize(Text text, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(text, jsonGen);
    jsonGen.writeEndObject();
  }
}
