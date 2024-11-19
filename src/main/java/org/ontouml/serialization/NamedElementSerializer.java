package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.ontouml.model.NamedElement;

import java.io.IOException;

public class NamedElementSerializer extends JsonSerializer<NamedElement> {

    @Override
  public void serialize(NamedElement element, JsonGenerator jsonGen, SerializerProvider provider)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(element, jsonGen);
    jsonGen.writeEndObject();
  }

  static void serializeFields(NamedElement element, JsonGenerator jsonGen) throws IOException {
    serializeId(element, jsonGen);
    jsonGen.writeObjectField("name", element.getName());
    jsonGen.writeObjectField("description", element.getDescription());
  }

  static void serializeId(NamedElement element, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeStringField("id", element.getId());
  }
}
