package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import com.google.common.truth.Truth8;
import org.ontouml.OntoumlElement;
import org.ontouml.model.Generalization;
import org.ontouml.model.GeneralizationSet;
import org.ontouml.model.Package;
import org.ontouml.model.Relation;
import org.ontouml.model.Class;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.utils.ResourceGetter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

class PackageDeserializerTest {

static String json;

static ObjectMapper mapper;

  static Package pkg;
  static ResourceGetter resourceGetter;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    resourceGetter = new ResourceGetter();
    File jsonFile = resourceGetter.getJsonFromDeserialization("project.allfields.ontouml.json");

    try {
        pkg = mapper.readValue(jsonFile, Package.class);

        System.out.println(pkg);

    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeId() {
    Truth.assertThat(pkg.getId()).isEqualTo("pk1");
  }

  @Test
  void shouldDeserializeName() {
    Truth8.assertThat(pkg.getNameIn("en")).hasValue("My Package");
  }

  void shouldDeserializeAlternativeNames() {
    Truth8.assertThat(pkg.getNameIn("en")).hasValue("My Package");
  }

  @Test
  void shouldDeserializeDescription() {
    Truth8.assertThat(pkg.getDescriptionIn("en")).hasValue("My description.");
  }

  @Test
  void shouldDeserializePropertyAssignments() {
    Optional<Object> pAssignment = pkg.getPropertyAssignment("uri");
    assertThat(pAssignment).hasValue("http://example.com/MyPackage");
  }

  @Test
  void shouldDeserializeContents() {
    assertThat(pkg.getContents()).hasSize(5);
  }

  @Test
  void shouldDeserializeClassInContents() {
    OntoumlElement element = pkg.getContents().get(0);
    assertThat(element).isInstanceOf(Class.class);
    assertThat(element.getId()).isEqualTo("c1");
    assertThat(element.getFirstName()).hasValue("Agent");
  }

  @Test
  void shouldDeserializeRelationInContents() {
    OntoumlElement element = pkg.getContents().get(1);
    assertThat(element).isInstanceOf(Relation.class);
    assertThat(element.getId()).isEqualTo("r1");
    assertThat(element.getFirstName()).hasValue("knows");
  }

  @Test
  void shouldDeserializeGeneralizationInContents() {
    OntoumlElement element = pkg.getContents().get(2);
    assertThat(element).isInstanceOf(Generalization.class);
    assertThat(element.getId()).isEqualTo("g1");
    assertThat(element.getFirstName()).hasValue("PersonToAgent");
  }

  @Test
  void shouldDeserializeGeneralizationSetInContents() {
    OntoumlElement element = pkg.getContents().get(3);
    assertThat(element).isInstanceOf(GeneralizationSet.class);
    assertThat(element.getId()).isEqualTo("gs1");
    assertThat(element.getFirstName()).hasValue("AgentNature");
  }

  @Test
  void shouldDeserializePackageInContents() {
    OntoumlElement element = pkg.getContents().get(4);
    assertThat(element).isInstanceOf(Package.class);
    assertThat(element.getId()).isEqualTo("pk2");
    assertThat(element.getFirstName()).hasValue("Subpackage");
  }

  @Test
  void shouldDeserializeSubPackageContents() {
    OntoumlElement element = pkg.getContents().get(4);
    assertThat(element).isInstanceOf(Package.class);

    List<OntoumlElement> contents = element.getContents();
    assertThat(contents).hasSize(1);
    assertThat(contents.get(0)).isInstanceOf(Class.class);
    assertThat(contents.get(0).getId()).isEqualTo("c2");
  }

  @Test
  void shouldResolveContainer() {
    OntoumlElement element = pkg.getContents().get(0);
    assertThat(element.getContainer()).hasValue(pkg);
  }
}
