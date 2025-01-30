package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Map;
import org.ontouml.ontouml4j.model.ModelElement;

public class ModelElementSerializer extends JsonSerializer<ModelElement> {

  public static void serializeFields(ModelElement element, JsonGenerator jsonGen)
      throws IOException {
    NamedElementSerializer.serializeFields(element, jsonGen);
    Map<String, Object> customProperties = element.getCustomProperties();

    if (customProperties == null) {
      jsonGen.writeNullField("customProperties");
      return;
    }

    jsonGen.writeObjectFieldStart("customProperties");
    customProperties.forEach(
        (key, value) -> {
          try {
            jsonGen.writeObjectField(key, value);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
    jsonGen.writeEndObject();
  }

  @Override
  public void serialize(ModelElement element, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(element, jsonGen);
    jsonGen.writeEndObject();
  }
}
