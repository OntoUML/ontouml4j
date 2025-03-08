package org.ontouml.ontouml4j.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.model.placeholders.UnresolvedPath;
import org.ontouml.ontouml4j.model.placeholders.UnresolvedView;
import org.ontouml.ontouml4j.model.view.BinaryConnectorView;

public class BinaryConnectorViewDeserializer {
  public static void deserialize(BinaryConnectorView view, JsonNode root, ObjectCodec codec)
      throws IOException, JacksonException {
    String sourceId = root.get("sourceView").asText();
    String targetId = root.get("targetView").asText();
    String pathId = root.get("path").asText();

    view.setSourceView(new UnresolvedView(sourceId));
    view.setTargetView(new UnresolvedView(targetId));
    view.setPath(new UnresolvedPath(pathId));

    ViewDeserializer.deserialize(view, root, codec);
  }
}
