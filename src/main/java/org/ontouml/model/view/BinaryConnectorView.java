package org.ontouml.model.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.shape.Path;

/**
 * A view element that represents the single occurrence of a binary connector (e.g., a binary
 * relation, or a generalization) in a diagram.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public abstract class BinaryConnectorView extends View {
  /** Identifies the source view the binary connector view connects in the diagram. */
  private View sourceView;

  /** Identifies the target view the binary connector view connects in the diagram. */
  private View targetView;

  /** Identifies the path shape that renders the binary connector in the diagram. */
  private Path path;
}
