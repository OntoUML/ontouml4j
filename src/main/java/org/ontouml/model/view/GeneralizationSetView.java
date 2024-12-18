package org.ontouml.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.view.GeneralizationSetViewDeserializer;
import org.ontouml.model.Project;
import org.ontouml.shape.Text;

/** A view element that represents the single occurrence of a generalization set in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = GeneralizationSetViewDeserializer.class)
public class GeneralizationSetView extends View {
  /**
   * Identifies the generalization views that are grouped by the generalization set view in the
   * diagram.
   */
  @Builder.Default List<GeneralizationView> generalizations = new ArrayList<>();

  /** Identifies the text shape that renders the generalization set view in the diagram. */
  private Text text;

  @Override
  public String getType() {
    return "GeneralizationSetView";
  }

  @Override
  public void resolveAllReferences(Project project) {
    super.resolveAllReferences(project);
    List<GeneralizationView> resolvedGeneralizations = new ArrayList<>();

    this.generalizations.forEach(
        generalization -> {
          Optional<GeneralizationView> genView =
              project.getElementById(generalization.getId(), GeneralizationView.class);
          genView.ifPresent(resolvedGeneralizations::add);
        });

    this.generalizations = resolvedGeneralizations;
  }
}
