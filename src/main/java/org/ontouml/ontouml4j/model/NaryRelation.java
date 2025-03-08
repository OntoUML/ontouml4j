package org.ontouml.ontouml4j.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.ontouml.ontouml4j.deserialization.NaryRelationDeserializer;
import org.ontouml.ontouml4j.model.stereotype.RelationStereotype;
import org.ontouml.ontouml4j.serialization.NaryRelationSerializer;

/**
 * A relation that defines the properties of a set of relations of the subject
 * domain that connect
 * more than two members. Examples include "studies in", "buys product from"
 * (ternary relation), and
 * derivation relations (e.g., between material relations and relators).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonSerialize(using = NaryRelationSerializer.class)
@JsonDeserialize(using = NaryRelationDeserializer.class)
public class NaryRelation extends Relation {

  public NaryRelation(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return "NaryRelation";
  }

  @Override
  public void setStereotype(String stereotypeName) {
    Optional<RelationStereotype> stereotype = RelationStereotype.findByName(stereotypeName);

    stereotype.ifPresentOrElse(
        s -> setOntoumlStereotype(stereotype.get()), () -> setCustomStereotype(stereotypeName));
  }
}
