package org.ontouml.deserialization;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.Element;
import org.ontouml.MultilingualText;
import org.ontouml.model.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    JsonNode alternativeNamesNode = root.get("alternativeNames");
    if (alternativeNamesNode != null) {
      List alternativeNames = alternativeNamesNode.traverse(codec).readValueAs(new TypeReference<List<MultilingualText>>() {});
      element.setAlternativeNames(alternativeNames);
      System.out.println("Deserialized alternativeNames: " + alternativeNames.toString());
    }

    JsonNode descNode = root.get("description");
    if (descNode != null) {
      MultilingualText desc = descNode.traverse(codec).readValueAs(MultilingualText.class);
      element.setDescription(desc);
      System.out.println("Deserialized description: " + desc);
    }

    JsonNode createdNode = root.get("created");
    if (createdNode != null) {
      Date created = createdNode.traverse(codec).readValueAs(Date.class);
      element.setCreated(created);
      System.out.println("Deserialized created: " + created);
    }

    JsonNode modifiedNode = root.get("modified");
    if (modifiedNode != null) {
      Date modified = modifiedNode.traverse(codec).readValueAs(Date.class);
      element.setModified(modified);
      System.out.println("Deserialized modified: " + modified);
    }

    JsonNode editorialNotesNode = root.get("editorialNotes");
    if (editorialNotesNode != null) {
      List<MultilingualText> editorialNotes = editorialNotesNode.traverse(codec).readValueAs(List.class);
      element.setEditorialNotes(editorialNotes);
      System.out.println("Deserialized editorial Notes: " + editorialNotes);
    }

    JsonNode creatorsNotesNode = root.get("creators");
    if (creatorsNotesNode != null) {
      List<Resource> creators = creatorsNotesNode.traverse(codec).readValueAs(List.class);
      element.setCreators(creators);
      System.out.println("Deserialized Creators: " + creators);
    }

    JsonNode contributorsNotesNode = root.get("contributors");
    if (contributorsNotesNode != null) {
      List<Resource> contributors = contributorsNotesNode.traverse(codec).readValueAs(List.class);
      element.setContributors(contributors);
      System.out.println("Deserialized Contributors: " + contributors);
    }
  }
}
