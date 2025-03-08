package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.ontouml.ontouml4j.model.OntoumlElement;
import org.ontouml.ontouml4j.model.Package;

public class PackageSerializer extends JsonSerializer<Package> {

  static void serializeFields(Package pkg, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeStringField("type", "Package");
    ModelElementSerializer.serializeFields(pkg, jsonGen);
    List<String> contentIds = pkg.getContents().stream().map(OntoumlElement::getId).toList();
    Serializer.writeEmptyableArrayField("contents", contentIds, jsonGen);
  }

  @Override
  public void serialize(Package pkg, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(pkg, jsonGen);
    jsonGen.writeEndObject();
  }
}
