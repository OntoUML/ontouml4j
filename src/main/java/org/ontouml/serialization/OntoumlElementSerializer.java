package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.OntoumlElement;

public class OntoumlElementSerializer extends JsonSerializer<OntoumlElement> {

  @Override
  public void serialize(
      OntoumlElement ontoumlElement,
      JsonGenerator jsonGen,
      SerializerProvider serializerProvider)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(ontoumlElement, jsonGen);
    jsonGen.writeEndObject();
  }

   static void serializeFields(OntoumlElement element, JsonGenerator jsonGen) throws IOException {
//    NamedElementSerializer.serializeFields(element, jsonGen);
    serializeType(element, jsonGen);
  }

  static void serializeType(OntoumlElement element, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeStringField("type", element.getType());
  }
}
