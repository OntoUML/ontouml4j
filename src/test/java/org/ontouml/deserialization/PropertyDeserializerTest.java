package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import com.google.common.truth.Truth8;
import org.ontouml.model.AggregationKind;
import org.ontouml.model.Class;
import org.ontouml.model.Property;
import org.ontouml.model.PropertyStereotype;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

class PropertyDeserializerTest {

  static String json =
      "{\n"
          + "   \"id\" : \"p1\",\n"
          + "   \"name\" : \"birthName\",\n"
          + "   \"description\" : null,\n"
          + "   \"type\" : \"Property\",\n"
          + "   \"propertyAssignments\" : null,\n"
          + "   \"stereotype\" : null,\n"
          + "   \"isDerived\" : true,\n"
          + "   \"isReadOnly\" : true,\n"
          + "   \"isOrdered\" : true,\n"
          + "   \"cardinality\" : \"1..*\",\n"
          + "   \"propertyType\" : {\n"
          + "     \"id\" : \"c1\",\n"
          + "     \"type\" : \"Class\"\n"
          + "   },\n"
          + "   \"subsettedProperties\" : [ {\n"
          + "     \"id\" : \"p2\",\n"
          + "     \"type\" : \"Property\"\n"
          + "   } ],\n"
          + "   \"redefinedProperties\" : [ {\n"
          + "     \"id\" : \"p3\",\n"
          + "     \"type\" : \"Property\"\n"
          + "   } ],\n"
          + "   \"aggregationKind\" : \"COMPOSITE\"\n"
          + "}";

  static ObjectMapper mapper;
  static Property property;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    property = mapper.readValue(json, Property.class);
  }

  @Test
  void shouldDeserializeReference() throws IOException {
    String jsonReference = "{ \"id\": \"p1\", \"type\":\"Property\"}";
    Property reference = mapper.readValue(jsonReference, Property.class);

    Truth.assertThat(reference.getId()).isEqualTo("p1");
    Truth8.assertThat(reference.getFirstName()).isEmpty();
    assertThat(reference.getStereotype()).isEmpty();
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(property.getId()).isEqualTo("p1");
  }

  @Test
  void shouldDeserializeIsDerived() {
    assertThat(property.isDerived()).isTrue();
  }

  @Test
  void shouldDeserializeIsReadOnly() {
    assertThat(property.isReadOnly()).isTrue();
  }

  @Test
  void shouldDeserializeIsOrdered() {
    assertThat(property.isOrdered()).isTrue();
  }

  @Test
  void shouldDeserializeCardinality() {
    assertThat(property.getCardinalityValue()).hasValue("1..*");
  }

  @Test
  void shouldDeserializePropertyType() {
    assertThat(property.getPropertyType()).isPresent();
    Truth.assertThat(property.getPropertyType().get()).isInstanceOf(Class.class);
    Truth.assertThat(property.getPropertyType().get().getId()).isEqualTo("c1");
  }

  @Test
  void shouldDeserializeSubsettedProperties() {
    assertThat(property.getSubsettedProperties()).hasSize(1);

    Property subsetted = property.getSubsettedProperties().get(0);
    Truth.assertThat(subsetted.getId()).isEqualTo("p2");
  }

  @Test
  void shouldDeserializeRedefinedProperties() {
    assertThat(property.getRedefinedProperties()).hasSize(1);

    Property redefined = property.getRedefinedProperties().get(0);
    Truth.assertThat(redefined.getId()).isEqualTo("p3");
  }

  @Test
  void shouldDeserializeAggregationKind() {
    assertThat(property.getAggregationKind()).hasValue(AggregationKind.COMPOSITE);
  }

  @Test
  void shouldDeserializeOntoumlStereotype() throws IOException {
    String json =
        "{\n"
            + "  \"id\": \"p1\",\n"
            + "  \"type\": \"Property\",\n"
            + "  \"stereotype\": \"begin\"\n"
            + "}";

    Property property = mapper.readValue(json, Property.class);
    assertThat(property.getOntoumlStereotype()).hasValue(PropertyStereotype.BEGIN);
    assertThat(property.getCustomStereotype()).isEmpty();
  }

  @Test
  void shouldDeserializeCustomStereotype() throws IOException {
    String json =
        "{\n"
            + "  \"id\": \"p1\",\n"
            + "  \"type\": \"Property\",\n"
            + "  \"stereotype\": \"custom\"\n"
            + "}";

    Property property = mapper.readValue(json, Property.class);
    assertThat(property.getOntoumlStereotype()).isEmpty();
    assertThat(property.getCustomStereotype()).hasValue("custom");
  }
}
