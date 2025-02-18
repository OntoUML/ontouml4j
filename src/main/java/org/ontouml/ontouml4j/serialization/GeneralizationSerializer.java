package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.Generalization;

public class GeneralizationSerializer extends JsonSerializer<Generalization> {
  static void serializeFields(Generalization value, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeStringField("type", "Generalization");

    PackageableElementSerializer.serializeFields(value, jsonGen);

    if (value.getGeneral().isEmpty()) {
      jsonGen.writeNullField("general");
    } else {
      Serializer.writeNullableStringField("general", value.getGeneral().get().getId(), jsonGen);
    }
    if (value.getSpecific().isEmpty()) {
      jsonGen.writeNullField("specific");
    } else {
      Serializer.writeNullableStringField("specific", value.getSpecific().get().getId(), jsonGen);
    }
  }

  @Override
  public void serialize(Generalization value, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(value, jsonGen);
    jsonGen.writeEndObject();
  }
}
