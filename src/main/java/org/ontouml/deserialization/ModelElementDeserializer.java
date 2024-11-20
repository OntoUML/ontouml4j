package org.ontouml.deserialization;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.ModelElement;

import java.io.IOException;
import java.util.Map;

public class ModelElementDeserializer {
  public static void deserialize(ModelElement element, JsonNode root, ObjectCodec codec)
      throws IOException {

    JsonNode node = root.get("customProperties");

    if (node != null && node.isObject()) {
      Map<String, Object> propertyMap =
          node.traverse(codec).readValueAs(new TypeReference<Map<String, Object>>() {});

      element.setCustomProperties(propertyMap);
    }
  }
}
