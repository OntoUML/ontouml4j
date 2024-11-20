package org.ontouml.shape;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Rectangle extends RectangularShape {
  @Override
  public String getType() {
    return "Rectangle";
  }
}
