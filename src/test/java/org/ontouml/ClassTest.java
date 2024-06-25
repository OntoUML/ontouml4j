package org.ontouml;

import org.ontouml.model.Class;
import org.ontouml.model.Literal;
import org.ontouml.model.Property;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ClassTest {

  @Test
  void shouldReturnAttributesAsContent() {
    Class person = Class.createKind("c1", "Person");
    Property name = person.createAttribute("p1", "name", null);
    Property age = person.createAttribute("p2", "age", null);

    List<OntoumlElement> contents = person.getContents();
    assertThat(contents).hasSize(2);
    assertThat(contents).isEqualTo(List.of(name, age));
  }

  @Test
  void shouldReturnLiteralsAsContent() {
    Class color = Class.createEnumeration("c1", "Color");
    Literal red = color.createLiteral("red");
    Literal blue = color.createLiteral("blue");

    List<OntoumlElement> contents = color.getContents();
    assertThat(contents).hasSize(2);
    assertThat(contents).isEqualTo(List.of(red, blue));
  }

  @Test
  void shouldBePrimitiveDatatype() {
    Class primitive = Class.createDatatype("1", "string");
    assertThat(primitive.isPrimitiveDatatype()).isTrue();
  }

  @Test
  void shouldNotBePrimitiveDatatype() {
    Class color = Class.createDatatype("1", "color");
    Class number = Class.createDatatype("1", "number");
    color.createAttribute("red", number);
    color.createAttribute("green", number);
    color.createAttribute("blue", number);

    assertThat(color.isPrimitiveDatatype()).isFalse();
  }
}
