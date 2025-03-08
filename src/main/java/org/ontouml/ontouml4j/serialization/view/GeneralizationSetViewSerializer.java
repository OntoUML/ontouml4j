package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.ontouml.ontouml4j.model.OntoumlElement;
import org.ontouml.ontouml4j.model.view.GeneralizationSetView;
import org.ontouml.ontouml4j.serialization.Serializer;
import org.ontouml.ontouml4j.shape.Text;

public class GeneralizationSetViewSerializer extends JsonSerializer<GeneralizationSetView> {
  public static void serializeFields(GeneralizationSetView view, JsonGenerator jsonGen)
      throws IOException {
    Serializer.writeNullableStringField("type", "GeneralizationSetView", jsonGen);
    List<String> generalizationIds = view.getGeneralizations().stream().map(OntoumlElement::getId).toList();
    Serializer.writeEmptyableArrayField("generalizations", generalizationIds, jsonGen);
    Text text = view.getText();
    if (text == null) {
      Serializer.writeNullableStringField("text", null, jsonGen);
    } else {
      Serializer.writeNullableStringField("text", view.getText().getId(), jsonGen);
    }
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
