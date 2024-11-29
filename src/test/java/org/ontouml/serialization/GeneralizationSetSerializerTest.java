package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Class;
import org.ontouml.model.GeneralizationSet;
import org.ontouml.model.Project;
import org.ontouml.utils.BuilderUtils;

public class GeneralizationSetSerializerTest {

  static Project project;
  static GeneralizationSet generalizationSet;

  @BeforeAll
  static void beforeAll() throws URISyntaxException {
    project = BuilderUtils.createProject();
    Optional<GeneralizationSet> gen = project.getGeneralizationSetById("generalizationSet_1");
    gen.ifPresent(value -> generalizationSet = value);
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    JsonNode node = generalizationSet.serialize();
    String id = node.get("id").asText();
    assertThat(id).isEqualTo("generalizationSet_1");
  }

  @Test
  void shouldSerializeType() throws JsonProcessingException {
    JsonNode node = generalizationSet.serialize();
    String type = node.get("type").asText();
    assertThat(type).contains("GeneralizationSet");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    generalizationSet.addName("pt", "Meu conjunto de generalizações");
    generalizationSet.addName("en", "My generalization set");
    JsonNode node = generalizationSet.serialize();

    String namePt = node.get("name").get("pt").asText();
    String nameEn = node.get("name").get("en").asText();

    assertThat(namePt).contains("Meu conjunto de generalizações");
    assertThat(nameEn).contains("My generalization set");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    generalizationSet.addDescription("pt", "Minha descrição.");
    generalizationSet.addDescription("en", "My description.");
    JsonNode node = generalizationSet.serialize();

    String descriptionPt = node.get("description").get("pt").asText();
    String descriptionEn = node.get("description").get("en").asText();

    assertThat(descriptionPt).contains("Minha descrição.");
    assertThat(descriptionEn).contains("My description.");
  }

  @Test
  void shouldSerializeIsDisjoint() throws JsonProcessingException {
    generalizationSet.setDisjoint(true);
    JsonNode node = generalizationSet.serialize();

    Boolean isDisjoint = Boolean.valueOf(node.get("isDisjoint").asText());

    assertThat(isDisjoint).isTrue();
  }

  @Test
  void shouldSerializeIsComplete() throws JsonProcessingException {
    generalizationSet.setComplete(true);
    JsonNode node = generalizationSet.serialize();

    Boolean isComplete = Boolean.valueOf(node.get("isComplete").asText());

    assertThat(isComplete).isTrue();
  }

  @Test
  void shouldSerializeEmptyCategorizerAsNull() throws JsonProcessingException {
    generalizationSet.setCategorizer(null);
    JsonNode node = generalizationSet.serialize();

    String categorizer = node.get("categorizer").asText();

    assertThat(categorizer).isEqualTo("null");
  }

  @Test
  void shouldSerializeCategorizer() throws JsonProcessingException {
    Class cat = Class.builder().id("class_1").build();
    generalizationSet.setCategorizer(cat);

    JsonNode node = generalizationSet.serialize();
    String categorizer = node.get("categorizer").asText();

    assertThat(categorizer).isEqualTo("class_1");
  }

  @Test
  void shouldSerializeGeneralizationReferences() throws JsonProcessingException {
    JsonNode node = generalizationSet.serialize();
    JsonNode generalizations = node.get("generalizations");

    List<String> gens = List.of(generalizations.get(0).asText(), generalizations.get(1).asText());

    assertThat(gens).contains("generalization_2");
    assertThat(gens).contains("generalization_1");
  }
}
