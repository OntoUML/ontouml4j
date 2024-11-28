package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.PackageableElement;

public class PackageableElementSerializer extends JsonSerializer<PackageableElement> {
  static void serializeFields(PackageableElement element, JsonGenerator jsonGen)
      throws IOException {
    NamedElementSerializer.serializeFields(element, jsonGen);
    OntoumlElementSerializer.serializeFields(element, jsonGen);
  }

  @Override
  public void serialize(
      PackageableElement element, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(element, jsonGen);
    jsonGen.writeEndObject();
  }
}
