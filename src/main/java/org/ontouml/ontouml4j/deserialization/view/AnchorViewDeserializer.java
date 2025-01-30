package org.ontouml.ontouml4j.deserialization.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.deserialization.*;
import org.ontouml.ontouml4j.model.view.AnchorView;

public class AnchorViewDeserializer extends JsonDeserializer<AnchorView> {

  @Override
  public AnchorView deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    AnchorView anchorView = new AnchorView();
    BinaryConnectorViewDeserializer.deserialize(anchorView, root, codec);
    return anchorView;
  }
}
