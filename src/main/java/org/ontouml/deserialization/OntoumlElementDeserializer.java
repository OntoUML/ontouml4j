package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.Date;
import org.ontouml.model.OntoumlElement;

public class OntoumlElementDeserializer {
  public static void deserialize(OntoumlElement element, JsonNode root, ObjectCodec codec)
      throws IOException, JacksonException {
    System.out.println("Deserializing type: " + root.get("type"));
    String id = root.get("id").asText();
    element.setId(id);
    System.out.println("Deserialized id:" + id);

    Date created = root.get("created").traverse(codec).readValueAs(Date.class);
    element.setCreated(created);
    System.out.println("Deserialized created:" + created.toString());

    Date modified = root.get("modified").traverse(codec).readValueAs(Date.class);
    element.setModified(modified);
    System.out.println("Deserialized modified:" + modified.toString());
  }
}
