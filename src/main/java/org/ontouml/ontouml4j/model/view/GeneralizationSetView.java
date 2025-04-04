package org.ontouml.ontouml4j.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.deserialization.view.GeneralizationSetViewDeserializer;
import org.ontouml.ontouml4j.model.GeneralizationSet;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.serialization.view.GeneralizationSetViewSerializer;
import org.ontouml.ontouml4j.shape.Text;

/**
 * A view element that represents the single occurrence of a generalization set
 * in a diagram.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonDeserialize(using = GeneralizationSetViewDeserializer.class)
@JsonSerialize(using = GeneralizationSetViewSerializer.class)
public class GeneralizationSetView extends View {
  /**
   * Identifies the generalization views that are grouped by the generalization
   * set view in the
   * diagram.
   */
  List<GeneralizationView> generalizations = new ArrayList<>();

  /**
   * Identifies the text shape that renders the generalization set view in the
   * diagram.
   */
  private Text text;

  public GeneralizationSetView(String id, GeneralizationSet genset, Text text,
      List<GeneralizationView> generalizations) {
    super(id, genset);
    this.text = text;
    this.generalizations = generalizations;
  }

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
          Optional<GeneralizationView> genView = project.getElementById(generalization.getId(),
              GeneralizationView.class);
          genView.ifPresent(resolvedGeneralizations::add);
        });

    this.generalizations = resolvedGeneralizations;
  }
}
