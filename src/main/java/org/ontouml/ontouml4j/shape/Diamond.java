package org.ontouml.ontouml4j.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.ontouml4j.deserialization.shape.DiamondDeserializer;
import org.ontouml.ontouml4j.serialization.shape.DiamondSerializer;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = DiamondDeserializer.class)
@JsonSerialize(using = DiamondSerializer.class)
public class Diamond extends RectangularShape {
  @Override
  public String getType() {
    return "Diamond";
  }
}
