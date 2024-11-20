package org.ontouml.model.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/** A view element that represents the single occurrence of a anchor in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class AnchorView extends BinaryConnectorView {

  @Override
  public String getType() {
    return "AnchorView";
  }
}
