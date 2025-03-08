package org.ontouml.ontouml4j.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.ontouml.ontouml4j.deserialization.shape.DiamondDeserializer;
import org.ontouml.ontouml4j.serialization.shape.DiamondSerializer;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonDeserialize(using = DiamondDeserializer.class)
@JsonSerialize(using = DiamondSerializer.class)
public class Diamond extends RectangularShape {

  public Diamond(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return "Diamond";
  }
}
