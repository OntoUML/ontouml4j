package org.ontouml.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.deserialization.OntoumlElementDeserializer;
import org.ontouml.shape.Rectangle;

public class RectangleDeserializer extends JsonDeserializer<Rectangle> {
  @Override
  public Rectangle deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Rectangle rectangle = new Rectangle();

    OntoumlElementDeserializer.deserialize(rectangle, root, codec);
    RectangularShapeDeserializer.deserialize(rectangle, root, codec);
    return rectangle;
  }
}
