package org.ontouml.ontouml4j.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.GeneralizationView;

public class GeneralizationViewDeserializer extends JsonDeserializer<GeneralizationView> {
  @Override
  public GeneralizationView deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    GeneralizationView generalizationView = new GeneralizationView();
    BinaryConnectorViewDeserializer.deserialize(generalizationView, root, codec);
    return generalizationView;
  }
}
