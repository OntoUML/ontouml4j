package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.MultilingualText;

public class MultilingualTextSerializer extends JsonSerializer<MultilingualText> {

  static void serializeFields(MultilingualText text, JsonGenerator jsonGen) throws IOException {
    if (text.isEmpty() || text.getText().isEmpty()) {
      jsonGen.writeNull();
      return;
    }
    text.getMap()
        .forEach(
            (key, value) -> {
              try {
                jsonGen.writeObjectField(key, value);
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
  }

  @Override
  public void serialize(
      MultilingualText text, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(text, jsonGen);
    jsonGen.writeEndObject();
  }
}
