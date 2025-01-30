package org.ontouml.ontouml4j.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.BinaryRelation;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.stereotype.RelationStereotype;

public class BinaryRelationSerializerTest {
  BinaryRelation relation;
  JsonNode node;

  @BeforeEach
  void setUp() throws JsonProcessingException, URISyntaxException {
    Class clazz = Class.builder().id("class_1").build();
    relation = new BinaryRelation("relation_1", "my relation", clazz, clazz);
    node = relation.serialize();
  }

  @Test
  void shouldSerializeId() {
    String id = node.get("id").asText();
    assertThat(id).isEqualTo("relation_1");
  }

  @Test
  void shouldSerializeType() {
    String type = node.get("type").asText();
    assertThat(type).isEqualTo("BinaryRelation");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    relation.addName("pt", "Minha relação");
    relation.addName("en", "My relation");
    node = relation.serialize();

    String namePt = node.get("name").get("pt").asText();
    String nameEn = node.get("name").get("en").asText();

    assertThat(namePt).isEqualTo("Minha relação");
    assertThat(nameEn).isEqualTo("My relation");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    relation.addDescription("pt", "Minha descrição.");
    relation.addDescription("en", "My description.");
    node = relation.serialize();

    String descriptionPt = node.get("description").get("pt").asText();
    String descriptionEn = node.get("description").get("en").asText();

    assertThat(descriptionPt).isEqualTo("Minha descrição.");
    assertThat(descriptionEn).isEqualTo("My description.");
  }

  @Test
  void shouldSerializeOntoumlStereotype() throws JsonProcessingException {
    relation.setOntoumlStereotype(RelationStereotype.MATERIAL);
    node = relation.serialize();

    String stereotype = node.get("stereotype").asText();

    assertThat(stereotype).isEqualTo("material");
  }

  @Test
  void shouldSerializeCustomStereotype() throws JsonProcessingException {
    relation.setCustomStereotype("custom");
    node = relation.serialize();

    String stereotype = node.get("stereotype").asText();

    assertThat(stereotype).isEqualTo("custom");
  }

  @Test
  void shouldSerializeIsAbstract() throws JsonProcessingException {
    relation.setAbstract(true);
    node = relation.serialize();

    Boolean isAbstract = node.get("isAbstract").booleanValue();

    assertThat(isAbstract).isTrue();
  }

  @Test
  void shouldSerializeIsDerived() throws JsonProcessingException {
    relation.setDerived(true);
    node = relation.serialize();

    Boolean isDerived = node.get("isDerived").booleanValue();

    assertThat(isDerived).isTrue();
  }

  @Test
  void shouldSerializeEnds() throws IOException {
    relation.getSourceEnd().setId("p0");
    relation.getSourceEnd().addName("source");
    relation.getTargetEnd().setId("p1");
    relation.getTargetEnd().addName("target");

    node = relation.serialize();
    String props = node.get("properties").toString();
    List<String> properties =
        new ObjectMapper().readValue(props, new TypeReference<List<String>>() {});

    assertThat(properties.getFirst()).isEqualTo("p0");
    assertThat(properties.get(1)).isEqualTo("p1");
  }
}
