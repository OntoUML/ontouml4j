package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.OntoumlElement;

import java.io.IOException;
import java.util.Date;

public class OntoumlElementDeserializer {
  public static void deserialize(OntoumlElement element, JsonNode root, ObjectCodec codec)
      throws IOException, JacksonException {
    String id = root.get("id").asText();
    element.setId(id);
    System.out.println("Deserialized id:" + id);

    JsonNode createdNode = root.get("created");
    if (createdNode != null) {
      Date created = createdNode.traverse(codec).readValueAs(Date.class);
      element.setCreated(created);
      System.out.println("Deserialized created:" + created.toString());
    }

    JsonNode modifiedNode = root.get("modified");
    if (modifiedNode != null) {
      Date modified = modifiedNode.traverse(codec).readValueAs(Date.class);
      element.setModified(modified);
      System.out.println("Deserialized modified:" + modified.toString());
    }
  }
}
