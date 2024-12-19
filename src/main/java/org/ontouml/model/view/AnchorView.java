package org.ontouml.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.view.AnchorViewDeserializer;
import org.ontouml.model.Project;

/** A view element that represents the single occurrence of a anchor in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = AnchorViewDeserializer.class)
public class AnchorView extends BinaryConnectorView {

  @Override
  public String getType() {
    return "AnchorView";
  }

  @Override
  public void resolveAllReferences(Project project) {
    super.resolveAllReferences(project);
  }
}
