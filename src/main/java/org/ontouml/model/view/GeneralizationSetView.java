package org.ontouml.model.view;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.shape.Text;

/** A view element that represents the single occurrence of a generalization set in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class GeneralizationSetView extends View {
  /**
   * Identifies the generalization views that are grouped by the generalization set view in the
   * diagram.
   */
  List<GeneralizationView> generalizations = new ArrayList<>();

  /** Identifies the text shape that renders the generalization set view in the diagram. */
  private Text text;

  @Override
  public String getType() {
    return "GeneralizationSetView";
  }
}
