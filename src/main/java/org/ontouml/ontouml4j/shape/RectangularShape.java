package org.ontouml.ontouml4j.shape;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.ontouml.ontouml4j.serialization.shape.RectangularShapeSerializer;

/** A shape defined by a top left position, a height, a width. */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = RectangularShapeSerializer.class)
public abstract class RectangularShape extends Shape {

  Point topLeft = new Point(0, 0);

  int width = 20;

  int height = 10;

  public RectangularShape(String id, Point topLeft, int width, int height) {
    this(id, width, height);
    this.topLeft = topLeft;
    this.width = width;
    this.height = height;
  }

  public RectangularShape(String id) {
    super(id);
    this.topLeft = new Point(0, 0);
    this.width = 0;
    this.height = 0;
  }

  public RectangularShape(String id, int width, int height) {
    super(id);
    this.topLeft = new Point(0, 0);
    this.width = width;
    this.height = height;
  }

  public RectangularShape(int width, int height) {
    this((String) null, width, height);
    this.topLeft = new Point(0, 0);
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
