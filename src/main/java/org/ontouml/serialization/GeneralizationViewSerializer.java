package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.ontouml.view.GeneralizationView;

import java.io.IOException;

public class GeneralizationViewSerializer extends JsonSerializer<GeneralizationView> {

  @Override
  public void serialize(
      GeneralizationView generalizationView, JsonGenerator jsonGen, SerializerProvider provider)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(generalizationView, jsonGen);
    jsonGen.writeEndObject();
  }

  static void serializeFields(GeneralizationView generalizationView, JsonGenerator jsonGen)
      throws IOException {
    ConnectorViewSerializer.serializeFields(generalizationView, jsonGen);
  }
}
