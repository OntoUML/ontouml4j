package org.ontouml.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.ontouml.model.OntoumlElement;
import org.ontouml.model.view.GeneralizationSetView;
import org.ontouml.serialization.Serializer;

public class GeneralizationSetViewSerializer extends JsonSerializer<GeneralizationSetView> {
  public static void serializeFields(GeneralizationSetView view, JsonGenerator jsonGen)
      throws IOException {
    List<String> generalizationIds =
        view.getGeneralizations().stream().map(OntoumlElement::getId).toList();
    Serializer.writeEmptyableArrayField("generalizations", generalizationIds, jsonGen);
    Serializer.writeNullableStringField("text", view.getText().getId(), jsonGen);
    ViewSerializer.serializeFields(view, jsonGen);
  }

  @Override
  public void serialize(
      GeneralizationSetView view, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(view, jsonGen);
    jsonGen.writeEndObject();
  }
}
