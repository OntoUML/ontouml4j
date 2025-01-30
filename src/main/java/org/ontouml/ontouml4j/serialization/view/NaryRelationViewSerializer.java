package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.ontouml.ontouml4j.model.OntoumlElement;
import org.ontouml.ontouml4j.model.view.NaryRelationView;
import org.ontouml.ontouml4j.serialization.Serializer;

public class NaryRelationViewSerializer extends JsonSerializer<NaryRelationView> {
  public static void serializeFields(NaryRelationView view, JsonGenerator jsonGen)
      throws IOException {
    ViewSerializer.serializeFields(view, jsonGen);
    List<String> memberIds = view.getMembers().stream().map(OntoumlElement::getId).toList();
    Serializer.writeNullableArrayField("members", memberIds, jsonGen);

    List<String> pathIds = view.getPaths().stream().map(OntoumlElement::getId).toList();
    Serializer.writeNullableArrayField("paths", pathIds, jsonGen);
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
