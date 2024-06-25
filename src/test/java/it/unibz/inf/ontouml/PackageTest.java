package it.unibz.inf.ontouml;

import com.google.common.truth.Truth;
import it.unibz.inf.ontouml.model.*;
import it.unibz.inf.ontouml.model.Class;
import it.unibz.inf.ontouml.model.Package;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth8.assertThat;

public class PackageTest {
  Project project = new Project();
  Package model = project.createModel();
  Package pkg = model.createPackage();
  Class clazz = model.createClass();
  Relation relation = model.createRelation(clazz, clazz);
  Generalization generalization = model.createGeneralization(clazz, clazz);
  GeneralizationSet genSet = model.createGeneralizationSet(generalization);

  @Test
  @DisplayName("createClass() should set the caller as the container of the new class.")
  void shouldPropagateCallerAsContainer() {
    assertThat(clazz.getContainer()).hasValue(model);
  }

  @Test
  @DisplayName("createClass() should set propagate the caller's project to the new class.")
  void shouldPropagateCallerAsProject() {
    assertThat(clazz.getProject()).hasValue(project);
  }

  @Test
  @DisplayName("createClass() should create a class without attributes.")
  void shouldHaveNoAttributes() {
    Truth.assertThat(clazz.getAttributes()).hasSize(0);
  }

  @Test
  @DisplayName("createEnumeration() should create a class with the Enumeration stereotype.")
  void shouldHaveEnumerationStereotype() {
    Class clazz = model.createEnumeration(new String[] {"Red", "Blue", "Green"});
    assertThat(clazz.getOntoumlStereotype()).hasValue(ClassStereotype.ENUMERATION);
  }

  @Test
  @DisplayName("createEnumeration() should create a class with the Enumeration stereotype.")
  void shouldCreateLiterals() {
    Class clazz = model.createEnumeration(new String[] {"Red", "Blue", "Green"});
    List<Literal> literals = clazz.getLiterals();

    Truth.assertThat(literals).hasSize(3);
    Truth.assertThat(literals).containsNoDuplicates();

    assertThat(literals.stream().map(l -> l.getFirstName().orElse("")))
        .containsExactly("Red", "Blue", "Green");
  }

  @Test
  @DisplayName("createRelation() should set the caller as the container of the new class.")
  void shouldPropagateCallerAsContainerOfRelation() {
    assertThat(relation.getContainer()).hasValue(model);
    ;
  }

  @Test
  @DisplayName("createRelation() should set propagate the caller's project to the new class.")
  void shouldPropagateCallerAsProjectOfRelation() {
    assertThat(relation.getProject()).hasValue(project);
    ;
  }

  @Test
  @DisplayName("createRelation() should create a class without attributes.")
  void shouldCreateBinaryRelation() {
    Truth.assertThat(relation.getProperties()).hasSize(2);
  }

  @Test
  @DisplayName("createPackage() should set the caller as the container of the new class.")
  void shouldPropagateCallerAsContainerOfPackage() {
    assertThat(pkg.getContainer()).hasValue(model);
  }

  @Test
  @DisplayName("createPackage() should set propagate the caller's project to the new class.")
  void shouldPropagateCallerAsProjectOfPackage() {
    assertThat(pkg.getProject()).hasValue(project);
  }

  @Test
  @DisplayName("createGeneralization() should set the caller as the container of the new class.")
  void shouldPropagateCallerAsContainerOfGeneralization() {
    assertThat(generalization.getContainer()).hasValue(model);
  }

  @Test
  @DisplayName("createGeneralization() should set propagate the caller's project to the new class.")
  void shouldPropagateCallerAsProjectOfGeneralization() {
    assertThat(generalization.getProject()).hasValue(project);
  }

  @Test
  @DisplayName("createGeneralizationSet() should set the caller as the container of the new class.")
  void shouldPropagateCallerAsContainerOfGeneralizationSet() {
    assertThat(genSet.getContainer()).hasValue(model);
  }

  @Test
  @DisplayName(
      "createGeneralizationSet() should set propagate the caller's project to the new class.")
  void shouldPropagateCallerAsProjectOfGeneralizationSet() {
    assertThat(genSet.getProject()).hasValue(project);
  }

  @Test
  void getContentsShouldReturnChildren() {
    Truth.assertThat(model.getContents()).containsExactly(pkg, clazz, relation, generalization, genSet);
  }

  @Test
  void modelShouldBeRoot() {
    Truth.assertThat(model.isRoot()).isTrue();
  }

  @Test
  void childPackageShouldNotBeRoot() {
    Truth.assertThat(pkg.isRoot()).isFalse();
  }
}
