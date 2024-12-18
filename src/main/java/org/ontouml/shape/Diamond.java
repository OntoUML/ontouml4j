package org.ontouml.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.shape.DiamondDeserializer;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = DiamondDeserializer.class)
public class Diamond extends RectangularShape {
  @Override
  public String getType() {
    return "Diamond";
  }
}
