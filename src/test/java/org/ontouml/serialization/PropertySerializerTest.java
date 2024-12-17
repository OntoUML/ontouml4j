package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URISyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Cardinality;
import org.ontouml.model.Class;
import org.ontouml.model.MultilingualText;
import org.ontouml.model.Property;
import org.ontouml.model.utils.AggregationKind;

public class PropertySerializerTest {

  Property property;
  JsonNode node;

  public PropertySerializerTest() throws JsonProcessingException {}

  @BeforeEach
  void beforeEach() throws URISyntaxException, JsonProcessingException {
    property =
        Property.builder()
            .id("property_1")
            .name(new MultilingualText("My Property"))
            .cardinality(new Cardinality("1", "*"))
            .isDerived(true)
            .isOrdered(true)
            .isReadOnly(true)
            .aggregationKind(AggregationKind.COMPOSITE)
            .build();
    node = property.serialize();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("property_1");
  }

  @Test
  void shouldSerializeType() throws JsonProcessingException {
    String type = node.get("type").asText();
    assertThat(type).isEqualTo("Property");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    property.addName("pt", "Minha propriedade");
    node = property.serialize();

    String namePt = node.get("name").get("pt").asText();
    String nameEn = node.get("name").get("en").asText();

    assertThat(namePt).contains("Minha propriedade");
    assertThat(nameEn).contains("My Property");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    property.addDescription("pt", "Minha descrição.");
    property.addDescription("en", "My description.");
    node = property.serialize();
    String descriptionPt = node.get("description").get("pt").asText();
    String descriptionEn = node.get("description").get("en").asText();

    assertThat(descriptionPt).isEqualTo("Minha descrição.");
    assertThat(descriptionEn).isEqualTo("My description.");
  }

  @Test
  void shouldSerializeEmptyPropertyTypeAsNull() throws JsonProcessingException {
    String propertyType = node.get("propertyType").textValue();
    assertThat(propertyType).isNull();
  }

  @Test
  void shouldSerializePropertyTypeReference() throws JsonProcessingException {
    Class clazz = new Class("c1", (String) null, (String) null);
    property.setPropertyType(clazz);

    node = property.serialize();

    String propertyType = node.get("propertyType").textValue();

    assertThat(propertyType).isEqualTo("c1");
  }

  @Test
  void shouldSerializeIsDerived() throws JsonProcessingException {
    property.setDerived(true);
    node = property.serialize();

    Boolean isDerived = node.get("isDerived").asBoolean();

    assertThat(isDerived).isTrue();
  }

  @Test
  void shouldSerializeIsOrdered() throws JsonProcessingException {
    property.setOrdered(true);
    node = property.serialize();

    Boolean isOrdered = node.get("isOrdered").asBoolean();

    assertThat(isOrdered).isTrue();
  }

  @Test
  void shouldSerializeIsReadOnly() throws JsonProcessingException {
    property.setReadOnly(true);
    node = property.serialize();

    Boolean isReadonly = node.get("isReadOnly").asBoolean();

    assertThat(isReadonly).isTrue();
  }

  @Test
  void shouldSerializeCardinality() throws JsonProcessingException {
    property.setCardinality("2..5");
    node = property.serialize();

    String cardinality = node.get("cardinality").textValue();

    assertThat(cardinality).isEqualTo("2..5");
  }

  //  @Test
  //  void shouldSerializeCardinalityOfOne() throws JsonProcessingException {
  //    property.setCardinality("1");
  //    json = mapper.writeValueAsString(property);
  //    assertThat(json).contains("\"cardinality\" : \"1\"");
  //  }
  //
  //  @Test
  //  void shouldSerializeCardinalityOfZeroToMany() throws JsonProcessingException {
  //    property.setCardinality("0..*");
  //    json = mapper.writeValueAsString(property);
  //    assertThat(json).contains("\"cardinality\" : \"0..*\"");
  //  }
  //
  //  //  @Test
  //  //  void shouldSerializeInvalidCardinality() throws JsonProcessingException {
  //  //    property.setCardinality("a..b");
  //  //    json = mapper.writeValueAsString(property);
  //  //    assertThat(json).contains("\"cardinality\" : \"a..b\"");
  //  //  }
  //
  //  @Test
  //  void shouldSerializeEmptySubsettedPropertiesAsNull() throws JsonProcessingException {
  //    assertThat(json).contains("\"subsettedProperties\" : [ ]");
  //  }

  //  @Test
  //  void shouldSerializeSubsettedProperties() throws JsonProcessingException {
  //    Class person = Class.createKind("c1", "Person");
  //
  //    Property property = new Property("p1", "ancestor", person);
  //    Property subProperty = new Property("p2", "father", person);
  //
  //    subProperty.addSubsettedProperty(property);
  //
  //    mapper = new ObjectMapper();
  //    json = mapper.writeValueAsString(subProperty);
  //
  //    assertThat(json).contains("\"subsettedProperties\":[{\"id\":\"p1\",\"type\":\"Property\"}");
  //  }
  //
  //  @Test
  //  void shouldSerializeRedefinedProperties() throws JsonProcessingException {
  //    Class person = Class.createKind("c1", "Person");
  //
  //    Property property = new Property("p1", "ancestor", person);
  //    Property subProperty = new Property("p2", "father", person);
  //
  //    subProperty.addRedefinedProperty(property);
  //
  //    mapper = new ObjectMapper();
  //    json = mapper.writeValueAsString(subProperty);
  //
  //    assertThat(json).contains("\"redefinedProperties\":[{\"id\":\"p1\",\"type\":\"Property\"}");
  //  }
}
