package org.ontouml.deserialization.shape;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import org.ontouml.deserialization.OntoumlElementDeserializer;
import org.ontouml.shape.Path;
import org.ontouml.shape.Point;

public class PathDeserializer extends JsonDeserializer<Path> {
  @Override
  public Path deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Path path = new Path();

    JsonNode pointsNode = root.get("points");
    if (pointsNode != null) {
      List<Point> points =
          pointsNode.traverse(codec).readValueAs(new TypeReference<List<Point>>() {});
      path.setPoints(points);
    }

    OntoumlElementDeserializer.deserialize(path, root, codec);
    return path;
  }
}
