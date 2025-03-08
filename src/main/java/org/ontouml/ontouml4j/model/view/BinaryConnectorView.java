package org.ontouml.ontouml4j.model.view;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.model.ModelElement;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.shape.Path;

/**
 * A view element that represents the single occurrence of a binary connector (e.g., a binary
 * relation, or a generalization) in a diagram.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BinaryConnectorView extends View {
  /** Identifies the source view the binary connector view connects in the diagram. */
  private View sourceView;

  /** Identifies the target view the binary connector view connects in the diagram. */
  private View targetView;

  /** Identifies the path shape that renders the binary connector in the diagram. */
  private Path path;

  public BinaryConnectorView(
      String id, View sourceView, View targetView, Path path, ModelElement isViewOf) {
    super(id);
    this.sourceView = sourceView;
    this.targetView = targetView;
    this.setIsViewOf(isViewOf);
    this.path = path;
  }

  public BinaryConnectorView(String id) {
    super(id);
  }

  @Override
  public void resolveAllReferences(Project project) {
    super.resolveAllReferences(project);
    Optional<View> sourceView = project.getElementById(this.sourceView.getId(), View.class);
    sourceView.ifPresent(this::setSourceView);

    Optional<View> targetView = project.getElementById(this.targetView.getId(), View.class);
    targetView.ifPresent(this::setTargetView);

    Optional<Path> path = project.getElementById(this.path.getId(), Path.class);
    path.ifPresent(this::setPath);
  }
}
