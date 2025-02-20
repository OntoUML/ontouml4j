package org.ontouml.ontouml4j.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import org.ontouml.ontouml4j.model.MultilingualText;
import org.ontouml.ontouml4j.model.NamedElement;
import org.ontouml.ontouml4j.model.Resource;

public class NamedElementDeserializer {

  public static void deserialize(NamedElement element, JsonNode root, ObjectCodec codec)
      throws IOException, JacksonException {

    JsonNode nameNode = root.get("name");
    if (nameNode != null) {
      MultilingualText name = nameNode.traverse(codec).readValueAs(MultilingualText.class);
      element.setName(name);
      System.out.println("Deserialized name: " + name);
    }

    JsonNode alternativeNamesNode = root.get("alternativeNames");
    if (alternativeNamesNode != null && !alternativeNamesNode.isNull()) {
      List<MultilingualText> alternativeNames =
          alternativeNamesNode
              .traverse(codec)
              .readValueAs(new TypeReference<List<MultilingualText>>() {});
      element.setAlternativeNames(alternativeNames);
      System.out.println("Deserialized alternativeNames: " + alternativeNames.toString());
    }

    JsonNode creatorsNotesNode = root.get("creators");
    if (creatorsNotesNode != null) {
      List<Resource> creators =
          creatorsNotesNode.traverse(codec).readValueAs(new TypeReference<List<Resource>>() {});
      element.setCreators(creators);
      System.out.println("Deserialized Creators: " + creators);
    }

    JsonNode contributorsNotesNode = root.get("contributors");
    if (contributorsNotesNode != null) {
      List<Resource> contributors =
          contributorsNotesNode.traverse(codec).readValueAs(new TypeReference<List<Resource>>() {});
      element.setContributors(contributors);
      System.out.println("Deserialized Contributors: " + contributors);
    }

    JsonNode descNode = root.get("description");
    if (descNode != null) {
      MultilingualText desc = descNode.traverse(codec).readValueAs(MultilingualText.class);
      element.setDescription(desc);
      System.out.println("Deserialized description: " + desc);
    }

    JsonNode editorialNotesNode = root.get("editorialNotes");
    if (editorialNotesNode != null) {
      List<MultilingualText> editorialNotes =
          editorialNotesNode
              .traverse(codec)
              .readValueAs(new TypeReference<List<MultilingualText>>() {});
      element.setEditorialNotes(editorialNotes);
      System.out.println("Deserialized editorial Notes: " + editorialNotes);
    }
  }
}
