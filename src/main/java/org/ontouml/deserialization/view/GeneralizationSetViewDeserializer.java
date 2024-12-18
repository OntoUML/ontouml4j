package org.ontouml.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import org.ontouml.model.view.GeneralizationSetView;
import org.ontouml.model.view.GeneralizationView;

public class GeneralizationSetViewDeserializer extends JsonDeserializer<GeneralizationSetView> {
  @Override
  public GeneralizationSetView deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    GeneralizationSetView generalizationView = new GeneralizationSetView();

    JsonNode generalizationsNode = root.get("generalizations");
    if (generalizationsNode != null) {
      List<String> members =
          generalizationsNode.traverse(codec).readValueAs(new TypeReference<List<String>>() {});
      List<GeneralizationView> generalizations =
          members.stream().map(GeneralizationView::new).toList();
      generalizationView.setGeneralizations(generalizations);
    }

    ViewDeserializer.deserialize(generalizationView, root, codec);
    return generalizationView;
  }
}
