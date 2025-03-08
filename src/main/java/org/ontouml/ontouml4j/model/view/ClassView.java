package org.ontouml.ontouml4j.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.deserialization.view.ClassViewDeserializer;
import org.ontouml.ontouml4j.model.ModelElement;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.serialization.view.ClassViewSerializer;
import org.ontouml.ontouml4j.shape.Rectangle;

/**
 * A view element that represents the single occurrence of a class in a diagram.
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonDeserialize(using = ClassViewDeserializer.class)
@JsonSerialize(using = ClassViewSerializer.class)
public class ClassView extends View {

  /**
   * Identifies the rectangle shape that renders the class view in the diagram.
   */
  private Rectangle rectangle = new Rectangle();

  public ClassView(String id, ModelElement isViewOf, Rectangle rectangle) {
    super(id, isViewOf);
    this.rectangle = rectangle;
  }

  public ClassView(String id, ModelElement isViewOf) {
    super(id, isViewOf);
  }

  public ClassView(String id) {
    super(id);
  }

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
    Rectangle rectangle = project.getElementById(getRectangle().getId(), Rectangle.class).orElse(null);
    this.setRectangle(rectangle);
  }

  @Override
  public void setProjectContainer(Project projectContainer) {
    super.setProjectContainer(projectContainer);
    this.rectangle.setProjectContainer(projectContainer);
    projectContainer.addElement(this.rectangle);
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

  public void setRectangle(Rectangle rectangle) {
    this.rectangle = rectangle;
  }
}
