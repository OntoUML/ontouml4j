package org.ontouml.ontouml4j.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.deserialization.shape.PathDeserializer;
import org.ontouml.ontouml4j.serialization.shape.PathSerializer;

/** A shape defined by a list of points connecting two other shapes. */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonDeserialize(using = PathDeserializer.class)
@JsonSerialize(using = PathSerializer.class)
public class Path extends Shape {

  /**
   * Determines the points across the path shape is rendered using an array of point objects. A path
   * has a minimum of two points which must be ordered from the edge of the source shape to the edge
   * of the target shape.
   */
  private List<Point> points = new ArrayList<>();

  public Path(String id, List<Point> points) {
    super(id);
    this.points = points;
  }

  public Path(String id) {
    super(id);
  }

  public void addPoints(List<Point> points) {
    if (points != null) points.forEach(this::addPoint);
  }

  public void moveTo(int x, int y) {
    points.add(new Point(x, y));
  }

  public void addPoint(Point point) {
    if (point != null) points.add(point);
  }

  @Override
  public String toString() {
    return points.toString();
  }

  @Override
  public String getType() {
    return "Path";
  }
}
