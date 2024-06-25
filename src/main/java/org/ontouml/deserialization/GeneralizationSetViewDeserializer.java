package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.GeneralizationSet;
import org.ontouml.view.GeneralizationSetView;
import org.ontouml.view.Text;

import java.io.IOException;

import static org.ontouml.deserialization.DeserializerUtils.deserializeObjectField;

public class GeneralizationSetViewDeserializer extends JsonDeserializer<GeneralizationSetView> {

  @Override
  public GeneralizationSetView deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    GeneralizationSetView view = new GeneralizationSetView();

    String id = root.get("id").asText();
    view.setId(id);

    GeneralizationSet gs =
        DeserializerUtils.deserializeObjectField(root, "modelElement", GeneralizationSet.class, codec);
    view.setModelElement(gs);

    Text shape = DeserializerUtils.deserializeObjectField(root, "shape", Text.class, codec);
    view.setShape(shape);

    return view;
  }
}
