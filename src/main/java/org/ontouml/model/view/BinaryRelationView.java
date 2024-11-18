package org.ontouml.model.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * A view element that represents the single occurrence of a binary relation in a diagram.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class BinaryRelationView extends BinaryConnectorView {
  @Override
  public String getType() {
    return "BinaryRelationView";
  }
}
