package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.PackageDeserializer;
import org.ontouml.model.stereotype.ClassStereotype;
import org.ontouml.serialization.PackageSerializer;

/**
 * A model element that can group other model elements that are referred to as "packageable
 * elements." Package elements are used to perform the modularization of an ontology. While the
 * OntoUML Metamodel does not require package elements to follow a tree structure (i.e., it allows
 * overlapping packages), ontologies that require UML representations should adhere to this
 * constraint for compatibility.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = PackageDeserializer.class)
@JsonSerialize(using = PackageSerializer.class)
public class Package extends PackageableElement {

  /** A reference to the Project that this package is contained */
  Project projectContainer;

  /** Identifies the contents of a package element. */
  @Builder.Default List<PackageableElement> contents = new ArrayList<>();

  /** List the ids of the contents of a package */
  @Builder.Default private List<String> contentIds = new ArrayList<>();

  public void setContents(List<PackageableElement> elements) {
    this.contentIds = elements.stream().map(PackageableElement::getId).toList();
    this.contents = elements;
  }

  public void setContents() {
    this.contentIds = null;
    this.contents = null;
  }

  @Override
  public String getType() {
    return "Package";
  }

  public void buildAllReferences(Project project) {
    for (String id : contentIds) {
      Optional<ModelElement> element = project.getElementById(id);
      element.ifPresent(item -> this.contents.add((PackageableElement) item));
    }
  }

  public Class createClass() {
    return createClass(null, null, (String) null);
  }

  public Class createClass(String name) {
    return createClass(null, name, (String) null);
  }

  public Class createClass(String id, String name, ClassStereotype stereotype) {
    return addContent(new Class(id, name, stereotype));
  }

  public Class createClass(String id, String name, String customStereotype) {
    return addContent(new Class(id, name, customStereotype));
  }

  private Class addContent(Class aClass) {
    contents.add(aClass);
    contentIds.add(aClass.getId());
    projectContainer.addClass(aClass);
    return aClass;
  }
}
