package org.ontouml.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * description: An identified element of an OntoUML ontology according to the OntoUML Metamodel,
 * which includes projects, model elements, diagrams, views, and shapes.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class OntoumlElement {
  /**
   * Determines the unique identifier for an OntoUML element in an ontology using a non-empty
   * string.
   */
  String id;

  /**
   * Determines when the element was created using a string in one of the following formats: year,
   * year-month, date, or date-time.
   */
  Date created;

  /**
   * Determines when the element was modified using a string in one of the following formats: year,
   * year-month, date, or date-time.
   */
  Date modified;

  /** The container in which the element is located. */
  private OntoumlElement container;

  public OntoumlElement(String id) {
    this(id, null, null, null);
  }

  public abstract String getType();
}
