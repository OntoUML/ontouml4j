package org.ontouml.shape;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.OntoumlElement;

/** An OntoUML element that identifies how to render a view (or a portion of one) in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public abstract class Shape extends OntoumlElement {

  public Shape(String id) {
    super(id);
  }
}
