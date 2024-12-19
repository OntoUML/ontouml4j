package org.ontouml.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.view.NaryRelationView;
import org.ontouml.serialization.Serializer;

public class NaryRelationViewSerializer extends JsonSerializer<NaryRelationView> {
  public static void serializeFields(NaryRelationView view, JsonGenerator jsonGen)
      throws IOException {
    ViewSerializer.serializeFields(view, jsonGen);

    Serializer.writeNullableArrayField("members", view.getMembers(), jsonGen);
    Serializer.writeNullableArrayField("paths", view.getPaths(), jsonGen);
    Serializer.writeNullableStringField("diamond", view.getDiamond().getId(), jsonGen);
  }

  @Override
  public void serialize(
      NaryRelationView view, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(view, jsonGen);
    jsonGen.writeEndObject();
  }
}
