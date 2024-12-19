package org.ontouml.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.shape.TextDeserializer;
import org.ontouml.serialization.shape.TextSerializer;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = TextDeserializer.class)
@JsonSerialize(using = TextSerializer.class)
public class Text extends RectangularShape {

  public Text(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return "Text";
  }
}
