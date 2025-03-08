package org.ontouml.ontouml4j.model.view;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.model.ModelElement;
import org.ontouml.ontouml4j.model.OntoumlElement;
import org.ontouml.ontouml4j.model.Project;

/**
 * An OntoUML element that represents a single model element in a diagram.
 *
 * <p>A view element connects a model element to the shapes in a diagram necessary to represent a
 * single occurrence of it. For example, an n-ary relation view connects a single relation element
 * to one diamond and a set of paths that represent a single occurrence of it in a diagram. Multiple
 * views can represent multiple occurrences of an element in the same diagram.
 *
 * <p>A view element is responsible for what portions of a model element are present in a single
 * diagram representation (e.g., whether the cardinality of a property is shown), unlike a shape,
 * which is responsible for aspects of the actual drawing (e.g., how to render a portion of a view,
 * in which position, and with which dimensions).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class View extends OntoumlElement {

  /** Identifies the model element that the view represents in the diagram. */
  private ModelElement isViewOf;

  public View(String id) {
    super(id);
  }

  public View(String id, ModelElement isViewOf) {
    super(id);
    this.isViewOf = isViewOf;
  }

  public void resolveAllReferences(Project project) {
    Optional<ModelElement> element = project.getElementById(isViewOf.getId(), ModelElement.class);

    element.ifPresent(this::setIsViewOf);
  }
}
