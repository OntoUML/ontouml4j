package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Date;
import org.ontouml.model.OntoumlElement;

public class OntoumlElementSerializer extends JsonSerializer<OntoumlElement> {

  public static void serializeFields(OntoumlElement element, JsonGenerator jsonGen)
      throws IOException {
    jsonGen.writeStringField("id", element.getId());
    serializeCreated(element, jsonGen);
    serializeModified(element, jsonGen);
  }

  static void serializeCreated(OntoumlElement element, JsonGenerator jsonGen) throws IOException {
    Date created = element.getCreated();
    if (created == null) {
      jsonGen.writeNullField("created");
    } else {
      jsonGen.writeStringField("created", element.getCreated().toString());
    }
  }

  static void serializeModified(OntoumlElement element, JsonGenerator jsonGen) throws IOException {
    Date created = element.getModified();
    if (created == null) {
      jsonGen.writeNullField("modified");
    } else {
      jsonGen.writeStringField("modified", element.getCreated().toString());
    }
  }

  @Override
  public void serialize(
      OntoumlElement element, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(element, jsonGen);
    jsonGen.writeEndObject();
  }
}
