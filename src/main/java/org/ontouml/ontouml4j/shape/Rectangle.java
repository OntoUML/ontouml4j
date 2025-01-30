package org.ontouml.ontouml4j.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.ontouml4j.deserialization.shape.RectangleDeserializer;
import org.ontouml.ontouml4j.serialization.shape.RectangleSerializer;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = RectangleDeserializer.class)
@JsonSerialize(using = RectangleSerializer.class)
public class Rectangle extends RectangularShape {

  public Rectangle(String id) {
    super(id, 0, 0);
  }

  public Rectangle(String id, int width, int height) {
    super(id, width, height);
  }

  @Override
  public String getType() {
    return "Rectangle";
  }
}
