package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.NamedElement;

public class NamedElementSerializer extends JsonSerializer<NamedElement> {

  static void serializeFields(NamedElement element, JsonGenerator jsonGen) throws IOException {
    OntoumlElementSerializer.serializeFields(element, jsonGen);
    serializeName(element, jsonGen);
    serializeDescription(element, jsonGen);
    serializeAlternativeNames(element, jsonGen);
    serializeCreators(element, jsonGen);
    serializeEditorialNotes(element, jsonGen);
    serializeContributors(element, jsonGen);
  }

  static void serializeName(NamedElement element, JsonGenerator jsonGen) throws IOException {
    if (element.getName() == null) {
      jsonGen.writeNullField("name");
      return;
    }
    jsonGen.writeObjectField("name", element.getName());
  }

  static void serializeDescription(NamedElement element, JsonGenerator jsonGen) throws IOException {
    if (element.getDescription() == null) {
      jsonGen.writeNullField("description");
      return;
    }
    jsonGen.writeObjectField("description", element.getDescription());
  }

  static void serializeAlternativeNames(NamedElement element, JsonGenerator jsonGen)
      throws IOException {
    if (element.getAlternativeNames() == null) {
      jsonGen.writeNullField("alternativeNames");
      return;
    }
    jsonGen.writeArrayFieldStart("alternativeNames");
    element
        .getAlternativeNames()
        .forEach(
            name -> {
              try {
                jsonGen.writeObject(name);
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
    jsonGen.writeEndArray();
  }

  static void serializeEditorialNotes(NamedElement element, JsonGenerator jsonGen)
      throws IOException {
    if (element.getEditorialNotes() == null) {
      jsonGen.writeNullField("editorialNotes");
      return;
    }
    jsonGen.writeArrayFieldStart("editorialNotes");
    element
        .getEditorialNotes()
        .forEach(
            name -> {
              try {
                jsonGen.writeObject(name);
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
    jsonGen.writeEndArray();
  }

  static void serializeCreators(NamedElement element, JsonGenerator jsonGen) throws IOException {
    if (element.getCreators() == null) {
      jsonGen.writeNullField("creators");
      return;
    }
    jsonGen.writeArrayFieldStart("creators");
    element
        .getCreators()
        .forEach(
            creator -> {
              try {
                jsonGen.writeObject(creator);
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
    jsonGen.writeEndArray();
  }

  static void serializeContributors(NamedElement element, JsonGenerator jsonGen)
      throws IOException {
    if (element.getContributors() == null) {
      jsonGen.writeNullField("contributors");
      return;
    }
    jsonGen.writeArrayFieldStart("contributors");
    element
        .getContributors()
        .forEach(
            creator -> {
              try {
                jsonGen.writeObject(creator);
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
    jsonGen.writeEndArray();
  }

  @Override
  public void serialize(NamedElement element, JsonGenerator jsonGen, SerializerProvider provider)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(element, jsonGen);
    jsonGen.writeEndObject();
  }
}
