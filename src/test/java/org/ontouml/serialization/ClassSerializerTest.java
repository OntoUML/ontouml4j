package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Class;
import org.ontouml.model.MultilingualText;
import org.ontouml.model.Nature;
import org.ontouml.model.Project;
import org.ontouml.model.stereotype.ClassStereotype;

public class ClassSerializerTest {
  static Project project;
  static Class clazz;
  ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  @BeforeAll
  static void beforeAll() throws IOException, ParseException, URISyntaxException {
    clazz =
        Class.builder()
            .name(new MultilingualText("class1"))
            .id("class_1")
            .ontoumlStereotype(ClassStereotype.KIND)
            .isAbstract(true)
            .build();
    Map<String, Class> classes =
        Map.of(
            "class_1",
            clazz,
            "class_2",
            Class.builder().name(new MultilingualText("class2")).id("class_2").build());
    project =
        Project.builder()
            .id("project_1")
            .created(new Date())
            .name(new MultilingualText("My Project"))
            .classes(classes)
            .build();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"id\" : \"class_1\"");
  }

  @Test
  void shouldSerializeType() throws JsonProcessingException {
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"type\" : \"Class\"");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    clazz.addName("pt", "Pessoa");
    clazz.addName("en", "Person");

    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"name\" : {");
    assertThat(json).contains("\"pt\" : \"Pessoa\"");
    assertThat(json).contains("\"en\" : \"Person\"");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    clazz.addDescription("pt", "Única espécie animal de primata bípede do género Homo ainda viva.");
    clazz.addDescription(
        "en", "Only bipedal primate animal species of the genus Homo still alive.");

    String json = mapper.writeValueAsString(clazz);

    assertThat(json).contains("\"description\" : {");
    assertThat(json)
        .contains("\"pt\" : \"Única espécie animal de primata bípede do género Homo ainda viva.\"");
    assertThat(json)
        .contains(
            "\"en\" : \"Only bipedal primate animal species of the genus Homo still alive.\"");
  }

  @Test
  void shouldSerializeOntoumlStereotype() throws JsonProcessingException {
    clazz.setOntoumlStereotype(ClassStereotype.KIND);
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"stereotype\" : \"kind\"");
  }

  @Test
  void shouldSerializeCustomStereotype() throws JsonProcessingException {
    clazz.setCustomStereotype("custom");
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"stereotype\" : \"custom\"");
  }

  @Test
  void shouldSerializeIsAbstract() throws JsonProcessingException {
    clazz.setAbstract(true);
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"isAbstract\" : true");
  }

  @Test
  void shouldSerializeIsDerived() throws JsonProcessingException {
    clazz.setDerived(true);
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"isDerived\" : true");
  }

  @Test
  void shouldSerializeEmptyAttributesAsNull() throws JsonProcessingException {
    clazz.setProperties(List.of());
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"properties\" : [ ]");
  }

  @Test
  void shouldSerializeAttributes() throws JsonProcessingException {
    clazz.createAttribute("a1", "father", clazz);
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"properties\" : [ \"a1\" ]");
  }

  @Test
  void shouldSerializeEmptyLiteralsAsNull() throws JsonProcessingException {
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"literals\" : [ ]");
  }

  @Test
  void shouldSerializeLiterals() throws JsonProcessingException {
    Class enumeration = Class.createEnumeration("1", "Color", "red", "green", "blue");
    String json = mapper.writeValueAsString(enumeration);
    assertThat(json).contains("\"literals\" : [ \"red\", \"green\", \"blue\" ]");
  }

  @Test
  void shouldSerializeEmptyRestrictedToAsNull() throws JsonProcessingException {
    clazz.setRestrictedTo(Optional.empty());
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"restrictedTo\" : [ ]");
  }

  @Test
  void shouldSerializeRestrictedTo() throws JsonProcessingException {
    clazz.setRestrictedTo(Nature.FUNCTIONAL_COMPLEX);
    String json = mapper.writeValueAsString(clazz);
    assertThat(json).contains("\"restrictedTo\" : [ \"functional-complex\" ]");
  }
}
