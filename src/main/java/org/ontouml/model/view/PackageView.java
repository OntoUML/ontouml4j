package org.ontouml.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.view.PackageViewDeserializer;
import org.ontouml.model.Project;
import org.ontouml.shape.Rectangle;

/** A view element that represents the single occurrence of a package in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = PackageViewDeserializer.class)
public class PackageView extends View {

  /** Identifies the rectangle shape that renders the package view in the diagram. */
  private Rectangle rectangle;

  @Override
  public String getType() {
    return "PackageView";
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
