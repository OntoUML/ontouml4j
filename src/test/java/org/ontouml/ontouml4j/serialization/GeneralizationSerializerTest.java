package org.ontouml.ontouml4j.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.Generalization;

public class GeneralizationSerializerTest {
  Generalization generalization;

  @BeforeEach
  void beforeEach() {
    Class general = new Class("class_1");
    Class specific = new Class("class_2");
    generalization = new Generalization("generalization_1", specific, general);
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
