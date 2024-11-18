package org.ontouml.model.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.shape.Shape;

/** A view element that represents the single occurrence of a class in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ClassView extends View {

  /** Identifies the rectangle shape that renders the class view in the diagram. */
  private Shape rectangle;

  @Override
  public String getType() {
    return "ClassView";
  }
}
