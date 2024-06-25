package org.ontouml.deserialization;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.Element;
import org.ontouml.MultilingualText;

import java.io.IOException;

public class ElementDeserializer {

  public static void deserialize(Element element, JsonNode root, ObjectCodec codec)
      throws IOException {
    System.out.println("Deserializing type: " + root.get("type") + "");
    String id = root.get("id").asText();
    element.setId(id);
    System.out.println("Deserialized id: " + id);

    JsonNode nameNode = root.get("name");
    if (nameNode != null) {
      MultilingualText name = nameNode.traverse(codec).readValueAs(MultilingualText.class);
      element.setName(name);
      System.out.println("Deserialized name: " + name);
    }

    JsonNode descNode = root.get("description");
    if (descNode != null) {
      MultilingualText desc = descNode.traverse(codec).readValueAs(MultilingualText.class);
      element.setDescription(desc);
      System.out.println("Deserialized description: " + desc);
    }
  }
}
