package org.ontouml.ontouml4j.deserialization.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import org.ontouml.ontouml4j.model.view.NaryRelationView;

public class NaryRelationViewDeserializer extends JsonDeserializer<NaryRelationView> {
  @Override
  public NaryRelationView deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    NaryRelationView relationView = new NaryRelationView();

    JsonNode membersNode = root.get("members");
    if (membersNode != null) {
      List<String> members =
          membersNode.traverse(codec).readValueAs(new TypeReference<List<String>>() {});
      relationView.setMemberIds(members);
    }

    JsonNode pathsNode = root.get("paths");
    if (pathsNode != null) {
      List<String> paths =
          pathsNode.traverse(codec).readValueAs(new TypeReference<List<String>>() {});
      relationView.setPathIds(paths);
    }

    String diamondId = root.get("diamond").asText();
    relationView.setDiamondId(diamondId);

    ViewDeserializer.deserialize(relationView, root, codec);

    return relationView;
  }
}
