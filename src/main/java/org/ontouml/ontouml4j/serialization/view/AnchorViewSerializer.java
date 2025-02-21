package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.AnchorView;

public class AnchorViewSerializer extends JsonSerializer<AnchorView> {
  public static void serializeFields(AnchorView view, JsonGenerator jsonGen) throws IOException {
    BinaryConnectorViewSerializer.serializeFields(view, jsonGen);
  }

  @Override
  public void serialize(AnchorView view, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(view, jsonGen);
    jsonGen.writeEndObject();
  }
}
