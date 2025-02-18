package org.ontouml.ontouml4j.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.MultilingualText;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.model.stereotype.ClassStereotype;

public class ClassSerializerTest {
  static Class clazz;
  JsonNode node;

  @BeforeAll
  static void beforeAll() throws IOException, ParseException, URISyntaxException {}

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Project project = new Project();
    clazz =
        Class.builder()
            .name(new MultilingualText("class1"))
            .id("class_1")
            .ontoumlStereotype(ClassStereotype.KIND)
            .isAbstract(true)
            .build();
    project.addClass(clazz);
    node = clazz.serialize();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("class_1");
  }

  @Test
  void shouldSerializeType() throws JsonProcessingException {
    String type = node.get("type").asText();

    assertThat(type).isEqualTo("Class");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    clazz.addName("pt", "Pessoa");
    clazz.addName("en", "Person");

    node = clazz.serialize();

    String namePt = node.get("name").get("pt").asText();
    String nameEn = node.get("name").get("en").asText();

    assertThat(namePt).isEqualTo("Pessoa");
    assertThat(nameEn).isEqualTo("Person");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    clazz.addDescription("pt", "Única espécie animal de primata bípede do género Homo ainda viva.");
    clazz.addDescription(
        "en", "Only bipedal primate animal species of the genus Homo still alive.");

    node = clazz.serialize();

    String descriptionPt = node.get("description").get("pt").asText();
    String descriptionEn = node.get("description").get("en").asText();

    assertThat(descriptionPt)
        .isEqualTo("Única espécie animal de primata bípede do género Homo ainda viva.");
    assertThat(descriptionEn)
        .isEqualTo("Only bipedal primate animal species of the genus Homo still alive.");
  }

  @Test
  void shouldSerializeOntoumlStereotype() throws JsonProcessingException {
    clazz.setOntoumlStereotype(ClassStereotype.KIND);
    node = clazz.serialize();

    String stereotype = node.get("stereotype").asText();

    assertThat(stereotype).isEqualTo("kind");
  }

  @Test
  void shouldSerializeCustomStereotype() throws JsonProcessingException {
    clazz.setCustomStereotype("custom");

    node = clazz.serialize();

    String stereotype = node.get("stereotype").asText();

    assertThat(stereotype).isEqualTo("custom");
  }

  @Test
  void shouldSerializeNullStereotype() throws JsonProcessingException {
    clazz.setCustomStereotype(null);

    node = clazz.serialize();

    String stereotype = node.get("stereotype").textValue();

    assertThat(stereotype).isNull();
  }

  @Test
  void shouldSerializeIsAbstract() throws JsonProcessingException {
    clazz.setAbstract(true);
    node = clazz.serialize();

    Boolean isAbstract = node.get("isAbstract").booleanValue();

    assertThat(isAbstract).isTrue();
  }

  @Test
  void shouldSerializeIsDerived() throws JsonProcessingException {
    clazz.setDerived(true);
    node = clazz.serialize();

    Boolean isDerived = node.get("isDerived").booleanValue();

    assertThat(isDerived).isTrue();
  }

  @Test
  void shouldSerializeEmptyAttributesAsNull() throws JsonProcessingException {
    clazz.setProperties(List.of());
    node = clazz.serialize();

    JsonNode properties = node.get("properties");
    List<String> props =
        new ObjectMapper().readValue(properties.toString(), new TypeReference<List<String>>() {});

    assertThat(props).hasSize(0);
  }

  @Test
  void shouldSerializeAttributes() throws JsonProcessingException {
    clazz.createAttribute("a1", "father", clazz);
    node = clazz.serialize();

    JsonNode properties = node.get("properties");
    List<String> props =
        new ObjectMapper().readValue(properties.toString(), new TypeReference<List<String>>() {});
    assertThat(props).hasSize(1);
    assertThat(props.getFirst()).isEqualTo("a1");
  }

  @Test
  void shouldSerializeEmptyLiteralsAsNull() throws JsonProcessingException {
    clazz.setLiterals(List.of());
    JsonNode literalsNode = node.get("literals");

    List<String> literals =
        new ObjectMapper().readValue(literalsNode.toString(), new TypeReference<List<String>>() {});
    assertThat(literals).hasSize(0);
  }

  @Test
  void shouldSerializeLiterals() throws JsonProcessingException {
    Class enumeration = Class.createEnumeration("1", "Color", "red", "green", "blue");
    JsonNode enumNode = enumeration.serialize();
    JsonNode literalsNode = enumNode.get("literals");

    List<String> literals =
        new ObjectMapper().readValue(literalsNode.toString(), new TypeReference<List<String>>() {});
    String name = enumNode.get("name").get("en").asText();
    String id = enumNode.get("id").asText();

    assertThat(id).isEqualTo("1");
    assertThat(name).isEqualTo("Color");
    assertThat(literals.getFirst()).isEqualTo("red");
    assertThat(literals.get(1)).isEqualTo("green");
    assertThat(literals.get(2)).isEqualTo("blue");
  }

  //  @Test
  //  void shouldSerializeEmptyRestrictedToAsNull() throws JsonProcessingException {
  //    clazz.setRestrictedTo(Optional.empty());
  //    String json = mapper.writeValueAsString(clazz);
  //    assertThat(json).contains("\"restrictedTo\" : [ ]");
  //  }
  //
  //  @Test
  //  void shouldSerializeRestrictedTo() throws JsonProcessingException {
  //    clazz.setRestrictedTo(Nature.FUNCTIONAL_COMPLEX);
  //    String json = mapper.writeValueAsString(clazz);
  //    assertThat(json).contains("\"restrictedTo\" : [ \"functional-complex\" ]");
  //  }
}
