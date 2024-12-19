package org.ontouml.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.view.ClassViewDeserializer;
import org.ontouml.model.Project;
import org.ontouml.serialization.view.ClassViewSerializer;
import org.ontouml.shape.Rectangle;

/** A view element that represents the single occurrence of a class in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = ClassViewDeserializer.class)
@JsonSerialize(using = ClassViewSerializer.class)
public class ClassView extends View {

  /** Identifies the rectangle shape that renders the class view in the diagram. */
  private Rectangle rectangle;

  @Override
  public String getType() {
    return "ClassView";
  }

  @Override
  public void resolveAllReferences(Project project) {
    super.resolveAllReferences(project);
    if (rectangle == null) {
      return;
    }
    Rectangle rectangle =
        project.getElementById(getRectangle().getId(), Rectangle.class).orElse(null);
    this.setRectangle(rectangle);
  }
}
