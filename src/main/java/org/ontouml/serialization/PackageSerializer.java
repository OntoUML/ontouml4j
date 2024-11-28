package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.Package;

public class PackageSerializer extends JsonSerializer<Package> {

  static void serializeFields(Package pkg, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeStringField("type", "Package");
    ModelElementSerializer.serializeFields(pkg, jsonGen);
    Serializer.writeEmptyableArrayField("contents", pkg.getContentIds(), jsonGen);
  }

  @Override
  public void serialize(Package pkg, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(pkg, jsonGen);
    jsonGen.writeEndObject();
  }
}
