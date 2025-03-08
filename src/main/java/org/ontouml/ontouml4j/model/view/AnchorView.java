package org.ontouml.ontouml4j.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.deserialization.view.AnchorViewDeserializer;
import org.ontouml.ontouml4j.model.ModelElement;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.serialization.view.AnchorViewSerializer;
import org.ontouml.ontouml4j.shape.Path;

/** A view element that represents the single occurrence of a anchor in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonDeserialize(using = AnchorViewDeserializer.class)
@JsonSerialize(using = AnchorViewSerializer.class)
public class AnchorView extends BinaryConnectorView {

    public AnchorView(String id, View sourceView, View targetView, Path path, ModelElement isViewOf ) {
        super(id, sourceView, targetView, path, isViewOf);
    }

  @Override
  public String getType() {
    return "AnchorView";
  }

  @Override
  public void resolveAllReferences(Project project) {
    super.resolveAllReferences(project);
  }
}
