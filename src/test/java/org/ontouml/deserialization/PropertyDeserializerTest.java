package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Property;
import org.ontouml.model.stereotype.PropertyStereotype;
import org.ontouml.model.utils.AggregationKind;
import org.ontouml.utils.ResourceGetter;

import java.io.File;
import java.io.IOException;

import static com.google.common.truth.Truth8.assertThat;

class PropertyDeserializerTest {

  static ObjectMapper mapper;
  static ResourceGetter resourceGetter = new ResourceGetter();
  static Property property;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    File jsonFile = resourceGetter.getJsonFromDeserialization("property.minimum.ontouml.json");
    try {
      property = mapper.readValue(jsonFile, Property.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeReference() throws IOException {
    String jsonReference = "{ \"id\": \"p1\", \"type\":\"Property\"}";
    Property reference = mapper.readValue(jsonReference, Property.class);

    Truth.assertThat(reference.getId()).isEqualTo("p1");
    assertThat(reference.getStereotype()).isEmpty();
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(property.getId()).isEqualTo("p1");
  }

  @Test
  void shouldDeserializeIsDerived() {
    Truth.assertThat(property.isDerived()).isTrue();
  }

  @Test
  void shouldDeserializeIsReadOnly() {
    Truth.assertThat(property.isReadOnly()).isTrue();
  }

  @Test
  void shouldDeserializeIsOrdered() {
    Truth.assertThat(property.isOrdered()).isTrue();
  }

  @Test
  void shouldDeserializeCardinality() {
    assertThat(property.getCardinalityValue()).hasValue("1..*");
  }

  @Test
  void shouldDeserializePropertyTypeId() {
    Truth.assertThat(property.getPropertyTypeId()).isEqualTo("class2");
  }

  @Test
  void shouldDeserializeSubsettedProperties() {
    Truth.assertThat(property.getSubsettedProperties()).hasSize(1);

    Property subsetted = property.getSubsettedProperties().getFirst();
    Truth.assertThat(subsetted.getId()).isEqualTo("p2");
  }

  @Test
  void shouldDeserializeRedefinedProperties() {
    Truth.assertThat(property.getRedefinedProperties()).hasSize(1);

    Property redefined = property.getRedefinedProperties().getFirst();
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