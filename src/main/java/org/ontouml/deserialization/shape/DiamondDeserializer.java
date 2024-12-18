package org.ontouml.deserialization.shape;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.deserialization.view.RectangularShapeDeserializer;
import org.ontouml.shape.Diamond;

public class DiamondDeserializer extends JsonDeserializer<Diamond> {
  @Override
  public Diamond deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Diamond diamond = new Diamond();
    RectangularShapeDeserializer.deserialize(diamond, root, codec);

    return diamond;
  }
}
