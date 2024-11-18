package org.ontouml.model.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.shape.Rectangle;

/** A view element that represents the single occurrence of a package in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class PackageView extends View {

  /** Identifies the rectangle shape that renders the package view in the diagram. */
  private Rectangle rectangle;

  @Override
  public String getType() {
    return "PackageView";
  }
}
