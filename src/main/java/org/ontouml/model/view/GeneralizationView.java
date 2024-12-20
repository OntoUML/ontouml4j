package org.ontouml.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.view.GeneralizationViewDeserializer;
import org.ontouml.model.Project;
import org.ontouml.serialization.view.GeneralizationViewSerializer;

/** A view element that represents the single occurrence of a binary relation in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = GeneralizationViewDeserializer.class)
@JsonSerialize(using = GeneralizationViewSerializer.class)
public class GeneralizationView extends BinaryConnectorView {
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
