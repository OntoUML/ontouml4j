package org.ontouml.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.shape.TextDeserializer;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = TextDeserializer.class)
public class Text extends RectangularShape {

  public Text(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return "Text";
  }
}
