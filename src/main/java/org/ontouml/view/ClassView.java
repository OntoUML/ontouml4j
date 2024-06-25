package org.ontouml.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ontouml.deserialization.ClassViewDeserializer;
import org.ontouml.model.Class;
import org.ontouml.serialization.ClassViewSerializer;

@JsonSerialize(using = ClassViewSerializer.class)
@JsonDeserialize(using = ClassViewDeserializer.class)
public class ClassView extends NodeView<Class, Rectangle> {
  public ClassView(String id, Class clazz) {
    super(id, clazz);
  }

  public ClassView(Class clazz) {
    this(null, clazz);
  }

  public ClassView() {
    this(null, null);
  }

  @Override
  public String getType() {
    return "ClassView";
  }

  @Override
  Rectangle createShape() {
    return new Rectangle();
  }
}
