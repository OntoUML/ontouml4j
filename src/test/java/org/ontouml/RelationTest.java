package org.ontouml;

import org.ontouml.model.Class;
import org.ontouml.model.Property;
import org.ontouml.model.Relation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class RelationTest {

  @Test
  void shouldReturnEndsAsContent() {
    Class person = Class.createKind("Person");
    Relation knows = Relation.createMaterial("knows", person, person);

    List<OntoumlElement> contents = knows.getContents();
    assertThat(contents).hasSize(2);

    contents.forEach(
        element -> {
          assertThat(element).isInstanceOf(Property.class);
        });
  }

  @Test
  void shouldHoldBetweenClasses() {
    Class person = Class.createKind("Person");
    Relation knows = Relation.createMaterial("knows", person, person);

    assertThat(knows.holdsBetweenClasses()).isTrue();
  }

  @Test
  void shouldNotHoldBetweenClasses() {
    Class person = Class.createKind("Person");
    Relation loves = Relation.createMaterial("loves", person, person);
    Class love = Class.createMode("Love");
    Relation derivation = Relation.createDerivation(love, loves);

    assertThat(derivation.holdsBetweenClasses()).isFalse();
  }
}
