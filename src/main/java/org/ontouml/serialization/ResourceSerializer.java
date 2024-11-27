package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.Resource;

public class ResourceSerializer extends JsonSerializer<Resource> {
  public static void serializeFields(Resource resource, JsonGenerator jsonGen) throws IOException {
    if (resource.getUri() == null) {
      jsonGen.writeNullField("uri");
    } else {
      jsonGen.writeStringField("uri", resource.getUri().toString());
    }
    if (resource.getName() == null) {
      jsonGen.writeNullField("name");
      return;
    }
    jsonGen.writeObjectField("name", resource.getName());
  }

  @Override
  public void serialize(Resource resource, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(resource, jsonGen);
    jsonGen.writeEndObject();
  }
}
