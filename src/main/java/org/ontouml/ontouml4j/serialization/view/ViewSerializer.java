package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.View;
import org.ontouml.ontouml4j.serialization.OntoumlElementSerializer;

public class ViewSerializer extends JsonSerializer<View> {

  static void serializeFields(View view, JsonGenerator jsonGen) throws IOException {
    OntoumlElementSerializer.serializeFields(view, jsonGen);
    jsonGen.writeStringField("isViewOf", view.getIsViewOf().getId());
  }

  @Override
  public void serialize(View view, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(view, jsonGen);
    jsonGen.writeEndObject();
  }
}
