package org.ontouml.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.view.BinaryRelationViewDeserializer;
import org.ontouml.serialization.view.BinaryRelationViewSerializer;

/** A view element that represents the single occurrence of a binary relation in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = BinaryRelationViewDeserializer.class)
@JsonSerialize(using = BinaryRelationViewSerializer.class)
public class BinaryRelationView extends BinaryConnectorView {
  @Override
  public String getType() {
    return "BinaryRelationView";
  }
}
