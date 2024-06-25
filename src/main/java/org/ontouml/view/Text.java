package org.ontouml.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ontouml.OntoumlElement;
import org.ontouml.deserialization.TextDeserializer;
import org.ontouml.serialization.TextSerializer;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = TextSerializer.class)
@JsonDeserialize(using = TextDeserializer.class)
public class Text extends RectangularShape {
  String value;

  public Text(String id, String value) {
    super(id);
    this.value = value;
  }

  public Text(String value) {
    this(null, value);
  }

  public Text() {
    this(null, null);
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public List<OntoumlElement> getContents() {
    return new ArrayList<>();
  }

  @Override
  public String getType() {
    return "Text";
  }
}
