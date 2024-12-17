package org.ontouml.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.model.view.BinaryConnectorView;

public class BinaryConnectorViewDeserializer {
  public static void deserialize(BinaryConnectorView view, JsonNode root, ObjectCodec codec)
      throws IOException, JacksonException {
    String id = root.get("sourceView").asText();
    view.setIsViewOfId(id);
    System.out.println("Deserialized isViewOf:" + id);
  }
}
