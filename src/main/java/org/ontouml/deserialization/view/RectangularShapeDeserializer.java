package org.ontouml.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.shape.Point;
import org.ontouml.shape.RectangularShape;

public class RectangularShapeDeserializer {
  public static void deserialize(RectangularShape shape, JsonNode root, ObjectCodec codec)
      throws IOException, JacksonException {

    String id = root.get("id").textValue();
    shape.setId(id);

    JsonNode topLeftNode = root.get("topLeft");
    if (topLeftNode != null) {
      Point point = topLeftNode.traverse(codec).readValueAs(Point.class);
      shape.setTopLeft(point);
    }
    int width = root.get("width").intValue();
    shape.setWidth(width);

    int height = root.get("height").intValue();
    shape.setHeight(height);
  }
}
