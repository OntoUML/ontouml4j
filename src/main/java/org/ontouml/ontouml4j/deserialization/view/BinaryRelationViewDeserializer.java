package org.ontouml.ontouml4j.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.BinaryRelationView;

public class BinaryRelationViewDeserializer extends JsonDeserializer<BinaryRelationView> {
  @Override
  public BinaryRelationView deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    BinaryRelationView relationView = new BinaryRelationView();
    BinaryConnectorViewDeserializer.deserialize(relationView, root, codec);
    return relationView;
  }
}
