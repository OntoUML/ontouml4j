package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import com.google.common.truth.Truth8;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.*;
import org.ontouml.model.Class;
import org.ontouml.model.Package;
import org.ontouml.utils.ResourceGetter;

public class PackageDeserializerTest {

  static ObjectMapper mapper;
  static ResourceGetter resourceGetter;
  static Package pkg;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    resourceGetter = new ResourceGetter();
    File jsonFile = resourceGetter.getJsonFromDeserialization("package.allfields.ontouml.json");

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
  void shouldDeserializeContents() {
    Truth.assertThat(pkg.getContents()).hasSize(6);
  }

  @Test
  void shouldDeserializeClassInContents() {
    PackageableElement element = pkg.getContents().get(0);
    Truth.assertThat(element).isInstanceOf(Class.class);
    assertThat(element.getId()).isEqualTo("class1");
    assertThat(element.getFirstName()).hasValue("Agent");
  }

  @Test
  void shouldDeserializeRelationInContents() {
    PackageableElement element = pkg.getContents().get(1);
    Truth.assertThat(element).isInstanceOf(Relation.class);
    assertThat(element.getId()).isEqualTo("relation1");
    assertThat(element.getFirstName()).hasValue("knows");
  }

  @Test
  void shouldDeserializeGeneralizationInContents() {
    PackageableElement element = pkg.getContents().get(3);
    Truth.assertThat(element).isInstanceOf(Generalization.class);
    assertThat(element.getId()).isEqualTo("generalization1");
    assertThat(element.getFirstName()).hasValue("PersonToAgent");
  }

  @Test
  void shouldDeserializeGeneralizationSetInContents() {
    PackageableElement element = pkg.getContents().get(4);
    Truth.assertThat(element).isInstanceOf(GeneralizationSet.class);
    assertThat(element.getId()).isEqualTo("generalizationSet1");
    assertThat(element.getFirstName()).hasValue("AgentNature");
  }

  @Test
  void shouldDeserializePackageInContents() {
    PackageableElement element = pkg.getContents().get(5);
    Truth.assertThat(element).isInstanceOf(Package.class);
    assertThat(element.getId()).isEqualTo("pk2");
    assertThat(element.getFirstName()).hasValue("Subpackage");
  }

  //
  //  @Test
  //  void shouldResolveContainer() {
  //    PackageableElement element = pkg.getContents().get(0);
  //    assertThat(element.getContainer()).isEqualTo(pkg);
  //  }
}
