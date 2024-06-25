package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.Generalization;
import org.ontouml.view.GeneralizationView;

import java.io.IOException;

import static org.ontouml.deserialization.DeserializerUtils.deserializeObjectField;

public class GeneralizationViewDeserializer extends JsonDeserializer<GeneralizationView> {

  @Override
  public GeneralizationView deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    GeneralizationView view = new GeneralizationView();

    ConnectorViewDeserializer.deserialize(view, root, codec);

    Generalization stub = DeserializerUtils.deserializeObjectField(root, "modelElement", Generalization.class, codec);
    view.setModelElement(stub);

    return view;
  }
}
