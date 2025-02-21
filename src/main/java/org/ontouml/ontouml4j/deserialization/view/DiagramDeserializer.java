package org.ontouml.ontouml4j.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.ontouml.ontouml4j.deserialization.NamedElementDeserializer;
import org.ontouml.ontouml4j.deserialization.OntoumlElementDeserializer;
import org.ontouml.ontouml4j.model.view.ClassView;
import org.ontouml.ontouml4j.model.view.Diagram;
import org.ontouml.ontouml4j.model.view.View;

public class DiagramDeserializer extends JsonDeserializer<Diagram> {
  @Override
  public Diagram deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Diagram diagram = new Diagram();

    NamedElementDeserializer.deserialize(diagram, root, codec);
    OntoumlElementDeserializer.deserialize(diagram, root, codec);

    deserializeContents(diagram, root, codec);

    return diagram;
  }

  private void deserializeContents(Diagram diagram, JsonNode root, ObjectCodec codec) {
    JsonNode contentsNode = root.get("views");

    if (contentsNode != null && contentsNode.isArray()) {
      try {
        List<String> contentIds =
            contentsNode.traverse(codec).readValueAs(new TypeReference<List<String>>() {});
        List<View> views =
            contentIds.stream()
                .map(item -> ClassView.builder().id(item).build())
                .collect(Collectors.toList());
        diagram.setViews(views);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
