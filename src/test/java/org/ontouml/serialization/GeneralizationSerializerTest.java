package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URISyntaxException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Generalization;
import org.ontouml.model.Project;
import org.ontouml.utils.BuilderUtils;

public class GeneralizationSerializerTest {
  static Project project;
  static Generalization generalization;

  @BeforeAll
  static void beforeAll() throws URISyntaxException {
    project = BuilderUtils.createProject();
    Optional<Generalization> gen = project.getGeneralizationById("generalization_1");
    gen.ifPresent(value -> generalization = value);
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    JsonNode node = generalization.serialize();
    String id = node.get("id").asText();
    assertThat(id).isEqualTo("generalization_1");
  }

  @Test
  void shouldSerializeType() throws JsonProcessingException {
    JsonNode node = generalization.serialize();
    String type = node.get("type").asText();
    assertThat(type).isEqualTo("Generalization");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    generalization.addName("pt", "Minha generalização");
    generalization.addName("en", "My generalization");
    JsonNode node = generalization.serialize();

    String namePt = node.get("name").get("pt").asText();
    String nameEn = node.get("name").get("en").asText();
    assertThat(namePt).contains("Minha generalização");
    assertThat(nameEn).contains("My generalization");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    generalization.addDescription("pt", "Minha descrição.");
    generalization.addDescription("en", "My description.");
    JsonNode node = generalization.serialize();

    String descriptionPt = node.get("description").get("pt").asText();
    String descriptionEn = node.get("description").get("en").asText();

    assertThat(descriptionPt).isEqualTo("Minha descrição.");
    assertThat(descriptionEn).contains("My description.");
  }

  @Test
  void shouldSerializeGeneralReference() throws JsonProcessingException {
    JsonNode node = generalization.serialize();

    String general = node.get("general").asText();

    assertThat(general).isEqualTo("class_1");
  }

  @Test
  void shouldSerializeSpecificReference() throws JsonProcessingException {
    JsonNode node = generalization.serialize();

    String general = node.get("specific").asText();

    assertThat(general).isEqualTo("class_2");
  }
}
