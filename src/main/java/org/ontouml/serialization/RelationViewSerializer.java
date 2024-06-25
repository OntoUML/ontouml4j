package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.ontouml.view.RelationView;

import java.io.IOException;

public class RelationViewSerializer extends JsonSerializer<RelationView> {

  @Override
  public void serialize(
      RelationView relationView, JsonGenerator jsonGen, SerializerProvider provider)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(relationView, jsonGen);
    jsonGen.writeEndObject();
  }

  static void serializeFields(RelationView relationView, JsonGenerator jsonGen) throws IOException {
    ConnectorViewSerializer.serializeFields(relationView, jsonGen);
  }
}
