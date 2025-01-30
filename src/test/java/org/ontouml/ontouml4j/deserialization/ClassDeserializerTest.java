package org.ontouml.ontouml4j.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.*;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.stereotype.ClassStereotype;
import org.ontouml.ontouml4j.utils.ResourceGetter;

class ClassDeserializerTest {

  static ObjectMapper mapper;
  static Class clazz;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    File jsonFile = ResourceGetter.getJsonFromDeserialization("class.allfields.ontouml.json");
    Project project = mapper.readValue(jsonFile, Project.class);
    Optional<Class> class1 = project.getClassById("class1");
    class1.ifPresent(aClass -> clazz = aClass);
  }

  //  @Test
  //  void shouldDeserializeReference() throws IOException {
  //    String jsonReference = "{ \"id\": \"class1\", \"type\":\"Class\"}";
  //    Class clazz = mapper.readValue(jsonReference, Class.class);
  //
  //    Truth.assertThat(clazz.getId()).isEqualTo("c1");
  //    assertThat(clazz.getFirstName()).isEmpty();
  //    assertThat(clazz.getStereotype()).isEmpty();
  //  }

  @Test
  void shouldDeserializeId() {
    assertThat(clazz.getId()).isEqualTo("class1");
  }

  @Test
  void shouldDeserializeName() {
    assertThat(clazz.getNameIn("en")).hasValue("My Class");
    assertThat(clazz.getNameIn("pt")).hasValue("Minha Classe");
  }

  @Test
  void shouldDeserializeDescription() {
    assertThat(clazz.getDescriptionIn("pt")).hasValue("Minha descrição.");
    assertThat(clazz.getDescriptionIn("en")).hasValue("My description.");
  }

  @Test
  void shouldDeserializeIsAbstract() {
    assertThat(clazz.isAbstract()).isTrue();
  }

  @Test
  void shouldDeserializeIsDerived() {
    assertThat(clazz.isDerived()).isTrue();
  }

  //  @Test
  //  void shouldDeserializeIsExtensional() {
  //    assertThat(clazz.isExtensional()).hasValue(true);
  //  }

  @Test
  void shouldDeserializeIsPowertype() {
    assertThat(clazz.isPowertype()).isTrue();
  }

  @Test
  void shouldDeserializeOrder() {
    assertThat(clazz.getOrder().get()).isEqualTo(1);
  }

  @Test
  void shouldDeserializeRestrictedTo() {
    assertThat(clazz.getRestrictedTo())
        .containsExactly(Nature.FUNCTIONAL_COMPLEX, Nature.COLLECTIVE, Nature.QUANTITY);
  }

  @Test
  void shouldDeserializeAllProperties() {
    List<Property> properties = clazz.getProperties();
    assertThat(properties).hasSize(2);
  }

  @Test
  void shouldDeserializePropertiesInTheCorrectOrder() {
    List<Property> properties = clazz.getProperties();
    Property a1 = properties.get(0);
    assertThat(a1.getId()).isEqualTo("a1");
    Property a2 = properties.get(1);
    assertThat(a2.getId()).isEqualTo("a2");
  }

  @Test
  void shouldDeserializePropertyData() {
    List<Property> properties = clazz.getProperties();
    Property a1 = properties.get(0);
    assertThat(a1.getId()).isEqualTo("a1");
    assertThat(a1.isDerived()).isTrue();
    assertThat(a1.isOrdered()).isTrue();
    assertThat(a1.isReadOnly()).isTrue();
    assertThat(a1.getFirstName()).hasValue("name");
    assertThat(a1.getCardinality().getValue()).hasValue("1..*");
    assertThat(a1.getPropertyType()).isPresent();
    assertThat(a1.getPropertyType().get().getId()).isEqualTo("class1");
  }

  @Test
  void shouldDeserializeLiterals() {
    assertThat(clazz.getLiterals()).hasSize(1);

    Literal literal = clazz.getLiterals().getFirst();
    assertThat(literal.getId()).isEqualTo("l1");
    assertThat(literal.getFirstName()).hasValue("red");
  }

  @Test
  void shouldDeserializeOntoumlStereotype() throws IOException {
    assertThat(clazz.getOntoumlStereotype()).hasValue(ClassStereotype.KIND);
    assertThat(clazz.getCustomStereotype()).isEmpty();
  }
}
