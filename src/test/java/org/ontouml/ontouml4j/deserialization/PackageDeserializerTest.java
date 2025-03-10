package org.ontouml.ontouml4j.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.*;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.Package;
import org.ontouml.ontouml4j.utils.ResourceGetter;

public class PackageDeserializerTest {

  static ObjectMapper mapper;
  Package pkg;

  @BeforeEach
  void setUp() throws IOException {
    mapper = new ObjectMapper();
    File jsonFile = ResourceGetter.getJsonFromDeserialization("package.allfields.ontouml.json");

    try {
      Project project = mapper.readValue(jsonFile, Project.class);
      Optional<Package> myPkg = project.getElementById("pk1", Package.class);
      myPkg.ifPresent(aPackage -> pkg = aPackage);

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
    assertThat(pkg.getNameIn("en")).hasValue("My Package");
  }

  void shouldDeserializeAlternativeNames() {
    assertThat(pkg.getNameIn("en")).hasValue("My Package");
  }

  @Test
  void shouldDeserializeDescription() {
    assertThat(pkg.getDescriptionIn("en")).hasValue("My description.");
  }

  @Test
  void shouldDeserializeContents() {
    Truth.assertThat(pkg.getContents()).hasSize(6);
  }

  @Test
  void shouldDeserializeClassInContents() {
    PackageableElement element = pkg.getElementById("class1").get();
    Truth.assertThat(element).isInstanceOf(Class.class);
    assertThat(element.getId()).isEqualTo("class1");
    assertThat(element.getFirstName()).hasValue("Agent");
  }

  @Test
  void shouldDeserializeRelationInContents() {
    PackageableElement element = pkg.getElementById("relation1").get();
    Truth.assertThat(element).isInstanceOf(Relation.class);
    assertThat(element.getId()).isEqualTo("relation1");
    assertThat(element.getFirstName()).hasValue("knows");
  }

  @Test
  void shouldDeserializeGeneralizationInContents() {
    PackageableElement element = pkg.getElementById("generalization1").get();
    Truth.assertThat(element).isInstanceOf(Generalization.class);
    assertThat(element.getId()).isEqualTo("generalization1");
    assertThat(element.getFirstName()).hasValue("PersonToAgent");
  }

  @Test
  void shouldDeserializeGeneralizationSetInContents() {
    PackageableElement element = pkg.getElementById("generalizationSet1").get();
    Truth.assertThat(element).isInstanceOf(GeneralizationSet.class);
    assertThat(element.getId()).isEqualTo("generalizationSet1");
    assertThat(element.getFirstName()).hasValue("AgentNature");
  }

  @Test
  void shouldDeserializePackageInContents() {
    PackageableElement element = pkg.getElementById("pk2").get();
    Truth.assertThat(element).isInstanceOf(Package.class);
    assertThat(element.getId()).isEqualTo("pk2");
    assertThat(element.getFirstName()).hasValue("Subpackage");
  }

  //
  // @Test
  // void shouldResolveContainer() {
  // PackageableElement element = pkg.getContents().get(0);
  // assertThat(element.getContainer()).isEqualTo(pkg);
  // }
}
