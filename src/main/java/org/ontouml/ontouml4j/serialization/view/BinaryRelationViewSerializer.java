package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.BinaryRelationView;
import org.ontouml.ontouml4j.serialization.Serializer;

public class BinaryRelationViewSerializer extends JsonSerializer<BinaryRelationView> {
  public static void serializeFields(BinaryRelationView view, JsonGenerator jsonGen)
      throws IOException {
    Serializer.writeNullableStringField("type", "BinaryRelationView", jsonGen);
    BinaryConnectorViewSerializer.serializeFields(view, jsonGen);
  }

  @Override
  public void serialize(
      BinaryRelationView view, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(view, jsonGen);
    jsonGen.writeEndObject();
  }
}
