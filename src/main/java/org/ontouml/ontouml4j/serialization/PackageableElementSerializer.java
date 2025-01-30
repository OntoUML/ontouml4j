package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.PackageableElement;

public class PackageableElementSerializer extends JsonSerializer<PackageableElement> {
  static void serializeFields(PackageableElement element, JsonGenerator jsonGen)
      throws IOException {
    ModelElementSerializer.serializeFields(element, jsonGen);
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
