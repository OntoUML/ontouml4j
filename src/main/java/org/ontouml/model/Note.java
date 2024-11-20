package org.ontouml.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * A model element that contains an annotation about the ontology or some of its elements. A note
 * can also be used to represent a constraint in both natural or structured language (i.e.,
 * first-order logic, or OCL).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Note extends PackageableElement {

  /** Determines the contents of a note using a language string. */
  private String text;

  @Override
  public String getType() {
    return "Note";
  }
}
