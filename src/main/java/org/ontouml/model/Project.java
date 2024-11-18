package org.ontouml.model;

import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * A named element that serves as the container of an entire OntoUML ontology, including the
 * elements of both the abstract syntax (i.e., model elements) and the concrete syntax (i.e.,
 * diagrams, view, and shapes).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Project extends NamedElement {
  /** Contains the OntoUML elements that are part of the project. */
  List<String> elements;

  @Override
  public String getType() {
    return "Project";
  }
}
