package org.ontouml.ontouml4j.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.deserialization.shape.TextDeserializer;
import org.ontouml.ontouml4j.serialization.shape.TextSerializer;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonDeserialize(using = TextDeserializer.class)
@JsonSerialize(using = TextSerializer.class)
public class Text extends RectangularShape {

  // TODO: Add text value?
  public String text;

  public Text(String id, Point topLeft, int width, int height, String text) {
    super(id, topLeft, width, height);
    this.text = text;
  }

  public Text(String id, Point topLeft, int width, int height) {
    super(id, topLeft, width, height);
  }

  public Text(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return "Text";
  }
}
