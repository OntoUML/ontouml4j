package org.ontouml.ontouml4j.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.ontouml4j.deserialization.view.GeneralizationViewDeserializer;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.serialization.view.GeneralizationViewSerializer;

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
