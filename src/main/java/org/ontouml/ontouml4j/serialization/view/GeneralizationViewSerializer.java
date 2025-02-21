package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.GeneralizationView;
import org.ontouml.ontouml4j.serialization.Serializer;

public class GeneralizationViewSerializer extends JsonSerializer<GeneralizationView> {
  public static void serializeFields(GeneralizationView view, JsonGenerator jsonGen)
      throws IOException {
    Serializer.writeNullableStringField("type", "GeneralizationView", jsonGen);
    BinaryConnectorViewSerializer.serializeFields(view, jsonGen);
  }

  @Override
  public void serialize(
      GeneralizationView view, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(view, jsonGen);
    jsonGen.writeEndObject();
  }
}
