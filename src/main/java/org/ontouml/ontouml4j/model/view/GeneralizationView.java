package org.ontouml.ontouml4j.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.deserialization.view.GeneralizationViewDeserializer;
import org.ontouml.ontouml4j.model.Generalization;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.serialization.view.GeneralizationViewSerializer;
import org.ontouml.ontouml4j.shape.Path;

/** A view element that represents the single occurrence of a binary relation in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonDeserialize(using = GeneralizationViewDeserializer.class)
@JsonSerialize(using = GeneralizationViewSerializer.class)
public class GeneralizationView extends BinaryConnectorView {

  public GeneralizationView(String id, Generalization gen, View source, View target, Path path) {
    super(id, source, target, path, gen);
  }

  public GeneralizationView(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return "GeneralizationView";
  }

  @Override
  public void resolveAllReferences(Project project) {
    super.resolveAllReferences(project);
  }
}
