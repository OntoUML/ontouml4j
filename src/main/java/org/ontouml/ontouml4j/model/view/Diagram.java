package org.ontouml.ontouml4j.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ontouml.ontouml4j.deserialization.view.DiagramDeserializer;
import org.ontouml.ontouml4j.model.ModelElement;
import org.ontouml.ontouml4j.model.NamedElement;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.serialization.view.DiagramSerializer;

/**
 * A named element that contains the visual representation (i.e., the concrete syntax) of an OntoUML
 * model or of a portion of it.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = DiagramDeserializer.class)
@JsonSerialize(using = DiagramSerializer.class)
public class Diagram extends NamedElement {

  /** Identifies the model element that is the owner of a diagram. */
  private ModelElement owner;

  /** Identifies the views contained in the diagram. */
  @Builder.Default private List<View> views = new ArrayList<>();

  @Override
  public String getType() {
    return "Diagram";
  }

  public void addElement(View diagramElement) {
    if (diagramElement == null) return;

    diagramElement.setContainer(this);
    diagramElement.setProjectContainer(getProjectContainer());

    if (this.getProjectContainer() != null) {
      this.getProjectContainer().addElement(diagramElement);
    }
    views.add(diagramElement);
  }

  public void addElements(Collection<? extends View> diagramElements) {
    if (diagramElements == null) return;
    diagramElements.stream().filter(Objects::nonNull).forEach(this::addElement);
  }

  @Override
  public void setProjectContainer(Project projectContainer) {
    super.setProjectContainer(projectContainer);
    this.views.forEach(view -> view.setProjectContainer(projectContainer));
    this.views.forEach(projectContainer::addElement);
  }

  public void resolveAllReferences(Project project) {
    List<View> newViews = new ArrayList<>();
    for (View view : views) {
      Optional<View> element = project.getElementById(view.getId(), View.class);
      element.ifPresent(newViews::add);
    }
    views = newViews;
  }
}
