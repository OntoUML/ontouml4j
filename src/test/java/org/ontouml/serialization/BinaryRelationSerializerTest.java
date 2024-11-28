package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.net.URISyntaxException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.BinaryRelation;
import org.ontouml.model.Class;
import org.ontouml.model.Project;
import org.ontouml.model.stereotype.RelationStereotype;
import org.ontouml.utils.BuilderUtils;

public class BinaryRelationSerializerTest {
  ObjectMapper mapper;
  Project project;
  Class clazz;
  BinaryRelation relation;
  String json;

  @BeforeEach
  void setUp() throws JsonProcessingException, URISyntaxException {
    project = BuilderUtils.createProject();
    mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    Optional<Class> class1 = project.getElementById("class_1", Class.class);
    class1.ifPresent(c -> clazz = c);
    relation = new BinaryRelation("r1", "my relation", clazz, clazz);
    json = mapper.writeValueAsString(relation);
  }

  @Test
  void shouldSerializeId() {
    assertThat(json).contains("\"id\" : \"r1\"");
  }

  @Test
  void shouldSerializeType() {
    assertThat(json).contains("\"type\" : \"BinaryRelation\"");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    relation.addName("pt", "Minha relação");
    relation.addName("en", "My relation");
    json = mapper.writeValueAsString(relation);

    assertThat(json).contains("\"name\" : {");
    assertThat(json).contains("\"pt\" : \"Minha relação\"");
    assertThat(json).contains("\"en\" : \"My relation\"");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    relation.addDescription("pt", "Minha descrição.");
    relation.addDescription("en", "My description.");
    json = mapper.writeValueAsString(relation);

    assertThat(json).contains("\"description\" : {");
    assertThat(json).contains("\"pt\" : \"Minha descrição.\"");
    assertThat(json).contains("\"en\" : \"My description.\"");
  }

  @Test
  void shouldSerializeOntoumlStereotype() throws JsonProcessingException {
    relation.setOntoumlStereotype(RelationStereotype.MATERIAL);
    json = mapper.writeValueAsString(relation);
    assertThat(json).contains("\"stereotype\" : \"material\"");
  }

  @Test
  void shouldSerializeCustomStereotype() throws JsonProcessingException {
    relation.setCustomStereotype("custom");
    json = mapper.writeValueAsString(relation);
    assertThat(json).contains("\"stereotype\" : \"custom\"");
  }

  @Test
  void shouldSerializeIsAbstract() throws JsonProcessingException {
    relation.setAbstract(true);
    json = mapper.writeValueAsString(relation);
    assertThat(json).contains("\"isAbstract\" : true");
  }

  @Test
  void shouldSerializeIsDerived() throws JsonProcessingException {
    relation.setDerived(true);
    String json = mapper.writeValueAsString(relation);
    assertThat(json).contains("\"isDerived\" : true");
  }

  @Test
  void shouldSerializeEnds() throws JsonProcessingException {
    relation.getSourceEnd().setId("p0");
    relation.getSourceEnd().addName("source");
    relation.getTargetEnd().setId("p1");
    relation.getTargetEnd().addName("target");

    mapper = new ObjectMapper();
    json = mapper.writeValueAsString(relation);

    assertThat(json).contains("\"properties\":[\"p0\",\"p1\"]");
  }
}
