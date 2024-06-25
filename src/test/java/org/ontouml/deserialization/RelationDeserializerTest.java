package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import com.google.common.truth.Truth8;
import org.ontouml.model.Class;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.*;

import java.io.IOException;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

class RelationDeserializerTest {

  static String json =
      "{\n"
          + "  \"id\" : \"r1\",\n"
          + "  \"name\" : \n{"
          + "    \"en\" : \"My Relation\",\n"
          + "    \"pt\" : \"Minha Relação\"\n"
          + "  },\n"
          + "  \"description\" : \n{"
          + "    \"en\" : \"My description.\",\n"
          + "    \"pt\" : \"Minha descrição.\"\n"
          + "  },\n"
          + "  \"type\" : \"Relation\",\n"
          + "  \"propertyAssignments\" : null,\n"
          + "  \"stereotype\" : \"material\",\n"
          + "  \"isAbstract\" : true,\n"
          + "  \"isDerived\" : true,\n"
          + "  \"properties\" : [ {\n"
          + "    \"id\" : \"p1\",\n"
          + "    \"name\" : \"person\",\n"
          + "    \"description\" : null,\n"
          + "    \"type\" : \"Property\",\n"
          + "    \"propertyAssignments\" : null,\n"
          + "    \"stereotype\" : null,\n"
          + "    \"isDerived\" : false,\n"
          + "    \"isReadOnly\" : false,\n"
          + "    \"isOrdered\" : false,\n"
          + "    \"cardinality\" : \"1..*\",\n"
          + "    \"propertyType\" : {\n"
          + "      \"id\" : \"c1\",\n"
          + "      \"type\" : \"Class\"\n"
          + "    },\n"
          + "    \"subsettedProperties\" : null,\n"
          + "    \"redefinedProperties\" : null,\n"
          + "    \"aggregationKind\" : null\n"
          + "  }, {\n"
          + "    \"id\" : \"p2\",\n"
          + "    \"name\" : \"car\",\n"
          + "    \"description\" : null,\n"
          + "    \"type\" : \"Property\",\n"
          + "    \"propertyAssignments\" : null,\n"
          + "    \"stereotype\" : null,\n"
          + "    \"isDerived\" : false,\n"
          + "    \"isReadOnly\" : false,\n"
          + "    \"isOrdered\" : false,\n"
          + "    \"cardinality\" : \"0..*\",\n"
          + "    \"propertyType\" : {\n"
          + "      \"id\" : \"c2\",\n"
          + "      \"type\" : \"Class\"\n"
          + "    },\n"
          + "    \"subsettedProperties\" : null,\n"
          + "    \"redefinedProperties\" : null,\n"
          + "    \"aggregationKind\" : null\n"
          + "  } ]\n"
          + "}";

  static ObjectMapper mapper;
  static Relation relation;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    relation = mapper.readValue(json, Relation.class);
  }

  @Test
  void shouldDeserializeReference() throws IOException {
    String jsonReference = "{ \"id\": \"r1\", \"type\":\"Relation\"}";
    Relation relation = mapper.readValue(jsonReference, Relation.class);

    Truth.assertThat(relation.getId()).isEqualTo("r1");
    Truth8.assertThat(relation.getFirstName()).isEmpty();
    assertThat(relation.getStereotype()).isEmpty();
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(relation.getId()).isEqualTo("r1");
  }

  @Test
  void shouldDeserializeName() {
    Truth8.assertThat(relation.getNameIn("en")).hasValue("My Relation");
    Truth8.assertThat(relation.getNameIn("pt")).hasValue("Minha Relação");
  }

  @Test
  void shouldDeserializeDescription() {
    Truth8.assertThat(relation.getDescriptionIn("en")).hasValue("My description.");
    Truth8.assertThat(relation.getDescriptionIn("pt")).hasValue("Minha descrição.");
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
    Truth.assertThat(relation.getProperties().get(0).getId()).isEqualTo("p1");
    Truth.assertThat(relation.getProperties().get(1).getId()).isEqualTo("p2");
  }

  @Test
  void shouldDeserializeSourceEnd() {
    Property sourceEnd = relation.getSourceEnd();
    Truth.assertThat(sourceEnd.getId()).isEqualTo("p1");
    Truth8.assertThat(sourceEnd.getFirstName()).hasValue("person");
  }

  @Test
  void shouldDeserializeTargetEnd() {
    Property targetEnd = relation.getTargetEnd();
    Truth.assertThat(targetEnd.getId()).isEqualTo("p2");
    Truth8.assertThat(targetEnd.getFirstName()).hasValue("car");
  }

  @Test
  void shouldDeserializeSource() {
    Classifier<?, ?> source = relation.getSource();
    Truth.assertThat(source.getId()).isEqualTo("c1");
    Truth.assertThat(source).isInstanceOf(Class.class);
  }

  @Test
  void shouldDeserializeTarget() {
    Classifier<?, ?> target = relation.getTarget();
    Truth.assertThat(target.getId()).isEqualTo("c2");
    Truth.assertThat(target).isInstanceOf(Class.class);
  }

  @Test
  void shouldDeserializeOntoumlStereotype() throws IOException {
    String json =
        "{\n"
            + "  \"id\": \"r1\",\n"
            + "  \"type\": \"Relation\",\n"
            + "  \"stereotype\": \"material\"\n"
            + "}";

    Relation relation = mapper.readValue(json, Relation.class);
    assertThat(relation.getOntoumlStereotype()).hasValue(RelationStereotype.MATERIAL);
    assertThat(relation.getCustomStereotype()).isEmpty();
  }

  @Test
  void shouldDeserializeCustomStereotype() throws IOException {
    String json =
        "{\n"
            + "  \"id\": \"r1\",\n"
            + "  \"type\": \"Relation\",\n"
            + "  \"stereotype\": \"custom\"\n"
            + "}";

    Relation relation = mapper.readValue(json, Relation.class);
    assertThat(relation.getOntoumlStereotype()).isEmpty();
    assertThat(relation.getCustomStereotype()).hasValue("custom");
  }
}
