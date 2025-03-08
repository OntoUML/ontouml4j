package org.ontouml.ontouml4j.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.Generalization;
import org.ontouml.ontouml4j.model.GeneralizationSet;
import org.ontouml.ontouml4j.model.MultilingualText;

public class GeneralizationSetSerializerTest {

  static GeneralizationSet generalizationSet;

  @BeforeEach
  void beforeEach() {
    Class general = new Class("class_1");
    Class specific = new Class("class_2");
    Generalization generalization1 = new Generalization("generalization_1", specific, general);

    Class general2 = new Class("class_3");
    Class specific2 = new Class("class_4");
    Generalization generalization2 = new Generalization("generalization_2", specific2, general2);

    generalizationSet = new GeneralizationSet("genset_1", "My Genset", List.of(generalization1, generalization2));
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    JsonNode node = generalizationSet.serialize();
    String id = node.get("id").asText();
    assertThat(id).isEqualTo("genset_1");
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
    Class cat = new Class("cat");
    generalizationSet.setCategorizer(cat);

    JsonNode node = generalizationSet.serialize();
    String categorizer = node.get("categorizer").asText();

    assertThat(categorizer).isEqualTo("cat");
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
