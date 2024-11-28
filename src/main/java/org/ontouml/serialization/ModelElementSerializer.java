package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.ModelElement;

public class ModelElementSerializer extends JsonSerializer<ModelElement> {

  @Override
  public void serialize(ModelElement element, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    NamedElementSerializer.serializeFields(element, jsonGen);
    OntoumlElementSerializer.serializeFields(element, jsonGen);
    serializeFields(element, jsonGen);
    jsonGen.writeEndObject();
  }

  public void serializeFields(ModelElement element, JsonGenerator jsonGen) {
    element
        .getCustomProperties()
        .forEach(
            (key, value) -> {
              try {
                jsonGen.writeObjectField(key, value);
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
  }
}
