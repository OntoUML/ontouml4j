package org.ontouml.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/** A model element that connects a note to a model element it concerns. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Anchor extends PackageableElement {
  /** Identifies the note the anchor connects. */
  private Note note;

  /** Identifies the model element the anchor connects. */
  private ModelElement element;

  @Override
  public String getType() {
    return "Anchor";
  }
}
