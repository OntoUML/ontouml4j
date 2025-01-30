package org.ontouml.ontouml4j.deserialization.shape;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.shape.Point;

public class PointDeserializer extends JsonDeserializer<Point> {
  @Override
  public Point deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    JsonNode root = parser.readValueAsTree();

    int x = root.get("x").intValue();
    int y = root.get("y").intValue();

    return new Point(x, y);
  }
}
