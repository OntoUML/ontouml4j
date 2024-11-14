package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.AggregationKind;
import org.ontouml.model.Property;
import org.ontouml.model.PropertyStereotype;
import org.ontouml.utils.ResourceGetter;

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
  void shouldDeserializePropertyTypeId() {
    assertThat(property.getPropertyTypeId()).isEqualTo("class2");
  }

  @Test
  void shouldDeserializeSubsettedProperties() {
    assertThat(property.getSubsettedProperties()).hasSize(1);

    Property subsetted = property.getSubsettedProperties().getFirst();
    Truth.assertThat(subsetted.getId()).isEqualTo("p2");
  }

  @Test
  void shouldDeserializeRedefinedProperties() {
    assertThat(property.getRedefinedProperties()).hasSize(1);

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
