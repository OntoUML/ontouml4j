package org.ontouml.ontouml4j.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.deserialization.view.BinaryRelationViewDeserializer;
import org.ontouml.ontouml4j.model.BinaryRelation;
import org.ontouml.ontouml4j.serialization.view.BinaryRelationViewSerializer;
import org.ontouml.ontouml4j.shape.Path;

/** A view element that represents the single occurrence of a binary relation in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonDeserialize(using = BinaryRelationViewDeserializer.class)
@JsonSerialize(using = BinaryRelationViewSerializer.class)
public class BinaryRelationView extends BinaryConnectorView {

  public BinaryRelationView(String id, View source, View target, Path path, BinaryRelation binaryRelation) {
    super(id, source, target, path, binaryRelation);
  }

  @Override
  public String getType() {
    return "BinaryRelationView";
  }
}
