package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.net.URISyntaxException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Project;
import org.ontouml.model.Property;

public class PropertySerializerTest {

  static Project project;
  static Property property;
  ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
  String json = mapper.writeValueAsString(property);

  public PropertySerializerTest() throws JsonProcessingException {}

  @BeforeAll
  static void beforeAll() throws URISyntaxException {
    project = BuilderUtils.createProject();
    Optional<Property> prop = project.getPropertyById("property1");
    prop.ifPresent(p -> property = p);
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    assertThat(json).contains("\"id\" : \"property_1\"");
  }

  @Test
  void shouldSerializeType() throws JsonProcessingException {
    assertThat(json).contains("\"type\" : \"Property\"");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    property.addName("pt", "Minha propriedade");
    property.addName("en", "My property");
    json = mapper.writeValueAsString(property);

    assertThat(json).contains("\"name\" : {");
    assertThat(json).contains("\"pt\" : \"Minha propriedade\"");
    assertThat(json).contains("\"en\" : \"My property\"");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    property.addDescription("pt", "Minha descrição.");
    property.addDescription("en", "My description.");
    json = mapper.writeValueAsString(property);

    assertThat(json).contains("\"description\" : {");
    assertThat(json).contains("\"pt\" : \"Minha descrição.\"");
    assertThat(json).contains("\"en\" : \"My description.\"");
  }

  @Test
  void shouldSerializeEmptyPropertyTypeAsNull() throws JsonProcessingException {
    assertThat(json).contains("\"propertyType\" : null");
  }

  //  @Test
  //  void shouldSerializePropertyTypeReference() throws JsonProcessingException {
  //    Class clazz = new Class("c1", (String) null, (String) null);
  //    property.setPropertyType(clazz);
  //
  //    mapper = new ObjectMapper();
  //    json = mapper.writeValueAsString(property);
  //
  //    assertThat(json).contains("\"propertyType\":{\"id\":\"c1\",\"type\":\"Class\"}");
  //  }

  @Test
  void shouldSerializeIsDerived() throws JsonProcessingException {
    property.setDerived(true);
    json = mapper.writeValueAsString(property);
    assertThat(json).contains("\"isDerived\" : true");
  }

  @Test
  void shouldSerializeIsOrdered() throws JsonProcessingException {
    property.setOrdered(true);
    json = mapper.writeValueAsString(property);
    assertThat(json).contains("\"isOrdered\" : true");
  }

  @Test
  void shouldSerializeIsReadOnly() throws JsonProcessingException {
    property.setReadOnly(true);
    json = mapper.writeValueAsString(property);
    assertThat(json).contains("\"isReadOnly\" : true");
  }

  @Test
  void shouldSerializeCardinality() throws JsonProcessingException {
    property.setCardinality("2..5");
    json = mapper.writeValueAsString(property);
    assertThat(json).contains("\"cardinality\" : \"2..5\"");
  }

  @Test
  void shouldSerializeCardinalityOfOne() throws JsonProcessingException {
    property.setCardinality("1");
    json = mapper.writeValueAsString(property);
    assertThat(json).contains("\"cardinality\" : \"1\"");
  }

  @Test
  void shouldSerializeCardinalityOfZeroToMany() throws JsonProcessingException {
    property.setCardinality("0..*");
    json = mapper.writeValueAsString(property);
    assertThat(json).contains("\"cardinality\" : \"0..*\"");
  }

  //  @Test
  //  void shouldSerializeInvalidCardinality() throws JsonProcessingException {
  //    property.setCardinality("a..b");
  //    json = mapper.writeValueAsString(property);
  //    assertThat(json).contains("\"cardinality\" : \"a..b\"");
  //  }

  @Test
  void shouldSerializeEmptySubsettedPropertiesAsNull() throws JsonProcessingException {
    assertThat(json).contains("\"subsettedProperties\" : [ ]");
  }

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
