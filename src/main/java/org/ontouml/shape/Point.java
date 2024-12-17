package org.ontouml.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.view.PointDeserializer;

/**
 * A object that represents a point in a diagram through (x,y) coordinates (horizontal and
 * vertical), where the top left corner of the diagram represents the coordinates (0,0) which
 * increase downwards and rightwards.
 */
@Getter
@Data
@SuperBuilder
@JsonDeserialize(using = PointDeserializer.class)
public class Point {
  /** Determines the horizontal coordinate of a point using a positive integer. */
  private int x;

  /** Determines the vertical coordinate of a point using a positive integer. */
  private int y;

  public Point(Integer x, Integer y) {
    setX(x);
    setY(y);
  }

  public void setX(Integer x) {
    this.x = (x != null) ? x : 0;
  }

  public void setY(Integer y) {
    this.y = (y != null) ? y : 0;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ')';
  }
}
