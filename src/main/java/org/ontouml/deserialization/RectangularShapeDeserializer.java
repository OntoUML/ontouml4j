package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.view.RectangularShape;

import static org.ontouml.deserialization.DeserializerUtils.deserializeNullableIntegerField;

public class RectangularShapeDeserializer {

  public static void deserialize(RectangularShape shape, JsonNode node) {

    String id = node.get("id").asText();
    shape.setId(id);

    Integer x = deserializeNullableIntegerField(node, "x");
    shape.setX(x);

    Integer y = deserializeNullableIntegerField(node, "y");
    shape.setY(y);

    Integer width = deserializeNullableIntegerField(node, "width");
    shape.setWidth(width);

    Integer height = deserializeNullableIntegerField(node, "height");
    shape.setHeight(height);
  }
}
