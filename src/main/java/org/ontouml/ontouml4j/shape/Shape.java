package org.ontouml.ontouml4j.shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.ontouml4j.model.OntoumlElement;

/** An OntoUML element that identifies how to render a view (or a portion of one) in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
public abstract class Shape extends OntoumlElement {

  public Shape(String id) {
    super(id);
  }
}
