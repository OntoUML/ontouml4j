package it.unibz.inf.ontouml.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.unibz.inf.ontouml.OntoumlElement;
import it.unibz.inf.ontouml.deserialization.RectangleDeserializer;
import it.unibz.inf.ontouml.serialization.RectangleSerializer;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = RectangleSerializer.class)
@JsonDeserialize(using = RectangleDeserializer.class)
public class Rectangle extends RectangularShape {

  public Rectangle(String id) {
    super(id);
  }

  public Rectangle() {
    this(null);
  }

  @Override
  public List<OntoumlElement> getContents() {
    return new ArrayList<>();
  }

  @Override
  public String getType() {
    return "Rectangle";
  }
}
