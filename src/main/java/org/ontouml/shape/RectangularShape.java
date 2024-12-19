package org.ontouml.shape;

import lombok.*;
import lombok.experimental.SuperBuilder;

/** A shape defined by a top left position, a height, a width. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class RectangularShape extends Shape {

  @Builder.Default Point topLeft = new Point(0, 0);

  @Builder.Default int width = 20;

  @Builder.Default int height = 10;

  public RectangularShape(String id) {
    super(id);
  }

  public RectangularShape(String id, int width, int height) {
    super(id);
    this.width = width;
    this.height = height;
  }

  public RectangularShape(int width, int height) {
    this((String) null, width, height);
    this.width = width;
    this.height = height;
  }

  public int getX() {
    return topLeft.getX();
  }

  public int getY() {
    return topLeft.getY();
  }
}
