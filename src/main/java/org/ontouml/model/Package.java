package org.ontouml.model;

import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * A model element that can group other model elements that are referred to as "packageable elements." Package elements
 * are used to perform the modularization of an ontology.
 * While the OntoUML Metamodel does not require package elements to follow a tree structure (i.e., it allows overlapping
 * packages), ontologies that require UML representations should adhere to this constraint for compatibility.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Package extends ModelElement {
  /**
   * Identifies the contents of a package element.
   */
  List<String> contents;

  @Override
  public String getType() {
    return "Package";
  }
}
