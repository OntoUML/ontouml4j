package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.BinaryRelation;
import org.ontouml.model.Class;
import org.ontouml.model.Classifier;
import org.ontouml.model.Project;
import org.ontouml.model.Property;
import org.ontouml.model.stereotype.RelationStereotype;
import org.ontouml.utils.ResourceGetter;

class BinaryRelationDeserializerTest {

  static ObjectMapper mapper;
  static Project project;
  static BinaryRelation relation;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    File jsonFile = ResourceGetter.getJsonFromDeserialization("relation.allfields.ontouml.json");

    try {
      project = mapper.readValue(jsonFile, Project.class);
      Optional<BinaryRelation> optionalBinaryRelation =
          project.getElementById("r1", BinaryRelation.class);
      optionalBinaryRelation.ifPresent(value -> relation = value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeReference() throws IOException {
    String jsonReference = "{ \"id\": \"r1\", \"type\":\"BinaryRelation\"}";
    BinaryRelation relation = mapper.readValue(jsonReference, BinaryRelation.class);

    Truth.assertThat(relation.getId()).isEqualTo("r1");
    Optional<String> name = relation.getFirstName();
    assertThat(relation.getFirstName()).isEmpty();
    assertThat(relation.getStereotype()).isEmpty();
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(relation.getId()).isEqualTo("r1");
  }

  @Test
  void shouldDeserializeName() {
    assertThat(relation.getNameIn("en")).hasValue("My BinaryRelation");
    assertThat(relation.getNameIn("pt")).hasValue("Minha Relação");
  }

  @Test
  void shouldDeserializeDescription() {
    assertThat(relation.getDescriptionIn("en")).hasValue("My description.");
    assertThat(relation.getDescriptionIn("pt")).hasValue("Minha descrição.");
  }

  @Test
  void shouldDeserializeIsAbstract() {
    assertThat(relation.isAbstract()).isTrue();
  }

  @Test
  void shouldDeserializeIsDerived() {
    assertThat(relation.isDerived()).isTrue();
  }

  @Test
  void shouldDeserializeEnds() {
    assertThat(relation.getProperties()).hasSize(2);
    Truth.assertThat(relation.getProperties().get(0).getId()).isEqualTo("end1");
    Truth.assertThat(relation.getProperties().get(1).getId()).isEqualTo("end2");
  }

  @Test
  void shouldDeserializeSourceEnd() {
    Property sourceEnd = relation.getSourceEnd();
    Truth.assertThat(sourceEnd.getId()).isEqualTo("end1");
    assertThat(sourceEnd.getFirstName()).hasValue("person");
  }

  @Test
  void shouldDeserializeTargetEnd() {
    Property targetEnd = relation.getTargetEnd();
    Truth.assertThat(targetEnd.getId()).isEqualTo("end2");
    assertThat(targetEnd.getFirstName()).hasValue("car");
  }

  @Test
  void shouldDeserializeSource() {
    Classifier<?, ?> source = relation.getSource();
    Truth.assertThat(source.getId()).isEqualTo("class1");
    Truth.assertThat(source).isInstanceOf(Class.class);
  }

  @Test
  void shouldDeserializeTarget() {
    Classifier<?, ?> target = relation.getTarget();
    Truth.assertThat(target.getId()).isEqualTo("class2");
    Truth.assertThat(target).isInstanceOf(Class.class);
  }

  @Test
  void shouldDeserializeOntoumlStereotype() throws IOException {
    String json =
        "{\n"
            + "  \"id\": \"r1\",\n"
            + "  \"type\": \"BinaryRelation\",\n"
            + "  \"stereotype\": \"material\"\n"
            + "}";

    BinaryRelation relation = mapper.readValue(json, BinaryRelation.class);
    assertThat(relation.getOntoumlStereotype()).hasValue(RelationStereotype.MATERIAL);
    assertThat(relation.getCustomStereotype()).isEmpty();
  }

  @Test
  void shouldDeserializeCustomStereotype() throws IOException {
    String json =
        "{\n"
            + "  \"id\": \"r1\",\n"
            + "  \"type\": \"BinaryRelation\",\n"
            + "  \"stereotype\": \"custom\"\n"
            + "}";

    BinaryRelation relation = mapper.readValue(json, BinaryRelation.class);
    assertThat(relation.getOntoumlStereotype()).isEmpty();
    assertThat(relation.getCustomStereotype()).hasValue("custom");
  }
}
