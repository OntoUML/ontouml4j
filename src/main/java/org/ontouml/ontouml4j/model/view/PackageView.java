package org.ontouml.ontouml4j.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.ontouml4j.deserialization.view.PackageViewDeserializer;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.serialization.view.PackageViewSerializer;
import org.ontouml.ontouml4j.shape.Rectangle;

/** A view element that represents the single occurrence of a package in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = PackageViewDeserializer.class)
@JsonSerialize(using = PackageViewSerializer.class)
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
