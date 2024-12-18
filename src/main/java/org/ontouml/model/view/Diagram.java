package org.ontouml.model.view;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.ModelElement;
import org.ontouml.model.NamedElement;

/**
 * A named element that contains the visual representation (i.e., the concrete syntax) of an OntoUML
 * model or of a portion of it.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Diagram extends NamedElement {

  /** Identifies the model element that is the owner of a diagram. */
  private ModelElement owner;

  /** Identifies the views contained in the diagram. */
  private List<View> views;

  @Override
  public String getType() {
    return "Diagram";
  }
}
